package org.autonoma.grupo01.webapp.expressgame.repositories;

import jakarta.inject.Inject;
import org.autonoma.grupo01.webapp.expressgame.annotations.MysqlConn;
import org.autonoma.grupo01.webapp.expressgame.annotations.Repository;
import org.autonoma.grupo01.webapp.expressgame.models.Cliente;
import org.autonoma.grupo01.webapp.expressgame.models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository{

    private final String SQL_SELECT = "SELECT * FROM vistacliente";

    private final String SQL_SELECT_BY_ID = "SELECT * FROM vistacliente WHERE idCliente = ?";

    private final String SQL_SELECT_BY_USER = "SELECT * FROM vistacliente WHERE idUsuario = ?";

    private final String SQL_SELECT_BY_ROL = "SELECT * FROM vistacliente WHERE idRol = ?";

    private final String SQL_INSERT = "INSERT INTO cliente(idUsuario, nombre, apellido, dni) VALUES (?, ?, ?, ?)";

    private final String SQL_UPDATE  = "UPDATE cliente SET idUsuario=?, nombre=?, apellido=?, dni=? WHERE idCLiente =?";

    private final String SQL_DELETE = "DELETE FROM cliente WHERE idCLiente=?";

    @Inject
    @MysqlConn
    private Connection conn;

    @Inject
    private UsuarioRepositoryImpl usuarioRepository;

    @Override
    public List<Cliente> listar() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();

        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_SELECT)){

            while (rs.next()){

                Cliente c = getCliente(rs);

                clientes.add(c);
            }
        }

        return clientes;
    }

    @Override
    public List<Cliente> listarPorRol(Integer idRol) throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_ROL)) {
            stmt.setInt(1, idRol);

            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Cliente c = getCliente(rs);
                    clientes.add(c);
                }
            }
        }
        return clientes;
    }

    @Override
    public Cliente porIdUsuario(Integer id) throws SQLException {
        Cliente c = null;
        try(PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_USER)){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    c = getCliente(rs);
                }
            }
        }

        return c;
    }

    @Override
    public Cliente porId(Integer id) throws SQLException {
        Cliente c = null;
        try(PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_ID)){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    c = getCliente(rs);
                }
            }
        }

        return c;
    }

    @Override
    public Cliente guardar(Cliente cliente) throws SQLException {
        String SQL;
        if(cliente.getId() != null && cliente.getId()>0){
            SQL = SQL_UPDATE;
        }else{
            SQL = SQL_INSERT;
        }
        try(PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setInt(1, cliente.getUsuario().getId());
            stmt.setString(2, cliente.getNombre());
            stmt.setString(3, cliente.getApellido());
            stmt.setString(4, cliente.getDni());

            if(cliente.getId()!=null && cliente.getId()>0){
                stmt.setInt(5, cliente.getId());
            }

            stmt.executeUpdate(); //Guardadndo los datos de la persona en la BD
        }

        return cliente;
    }

    @Override
    public void eliminar(Integer id) throws SQLException {
        try(PreparedStatement stmt = conn.prepareStatement(SQL_DELETE)){
            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }

    public Cliente getCliente(ResultSet rs) throws SQLException {
        Cliente c = new Cliente();

        //Tabla cliente
        c.setId(rs.getInt("idCliente"));
        c.setNombre(rs.getString("nombre"));
        c.setApellido(rs.getString("apellido"));
        c.setDni(rs.getString("dni"));

        //Tabla usuario
        Usuario u = usuarioRepository.getUsuario(rs);

        c.setUsuario(u);

        return c;
    }
}