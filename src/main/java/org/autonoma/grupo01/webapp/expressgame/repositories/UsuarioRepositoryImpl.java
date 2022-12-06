package org.autonoma.grupo01.webapp.expressgame.repositories;

import jakarta.inject.Inject;
import org.autonoma.grupo01.webapp.expressgame.annotations.MysqlConn;
import org.autonoma.grupo01.webapp.expressgame.annotations.Repository;
import org.autonoma.grupo01.webapp.expressgame.models.Rol;
import org.autonoma.grupo01.webapp.expressgame.models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final String SQL_SELECT = "SELECT u.*, r.nombre AS rol FROM usuario AS u INNER JOIN rol AS r ON (u.idRol = r.idRol)";
    private final String SQL_SELECT_BY_ID = "SELECT u.*, r.nombre AS rol FROM usuario AS u INNER JOIN rol AS r ON (u.idRol = r.idRol) WHERE u.idUsuario = ?";
    private final String SQL_SELECT_BY_USERNAME_OR_EMAIL = "SELECT u.*, r.nombre AS rol FROM usuario AS u INNER JOIN rol AS r ON (u.idRol = r.idRol) WHERE u.username = ? OR u.email = ?";
    private final String SQL_INSERT = "INSERT INTO usuario(idRol, username, email, password) VALUES (?, ?, ?, ?)";

    private final String SQL_UPDATE = "UPDATE usuario SET idRol=?, username=?, email=?, password=? WHERE idUsuario=?";

    private final String SQL_DELETE = "DELETE FROM usuario WHERE idUsuario=?";

    @Inject
    @MysqlConn
    private Connection conn;

    @Override
    public List<Usuario> listar() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_SELECT)) {
            while (rs.next()) {

                Usuario u = getUsuario(rs);

                usuarios.add(u);
            }
        }
        return usuarios;
    }

    @Override
    public Usuario porId(Integer id) throws SQLException {
        Usuario u = null;

        try (PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_ID)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    u = getUsuario(rs);
                }
            }
        }

        return u;
    }

    @Override
    public Usuario guardar(Usuario usuario) throws SQLException {
        try (PreparedStatement stmt = (usuario.getId() != null && usuario.getId()>0) ?
                conn.prepareStatement(SQL_UPDATE) : conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, usuario.getRol().getId());
            stmt.setString(2, usuario.getUsername());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getPassword());

            if(usuario.getId() != null && usuario.getId()>0){

                stmt.setInt(5, usuario.getId());

                stmt.executeUpdate();

            } else {

                stmt.executeUpdate();//Guardadndo los datos de la persona en la BD

                //Obtenemos el id generado para luego se inserte junto con el cliente
                try(ResultSet rs = stmt.getGeneratedKeys()){
                    if(rs.next()){
                        usuario.setId(rs.getInt(1));
                    }
                }
            }
        }

        return usuario;
    }

    @Override
    public void eliminar(Integer id) throws SQLException {
        try(PreparedStatement stmt = conn.prepareStatement(SQL_DELETE)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Usuario porUsernameOrEmail(String usernameOrEmail) throws SQLException {
        Usuario u = null;
        try(PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_USERNAME_OR_EMAIL)){
            stmt.setString(1, usernameOrEmail);
            stmt.setString(2, usernameOrEmail);

            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    u = getUsuario(rs);
                }
            }
        }
        return u;
    }

    public Usuario getUsuario(ResultSet rs) throws SQLException {
        //Tabla usuario
        Usuario u = new Usuario();
        u.setId(rs.getInt("idUsuario"));
        u.setUsername(rs.getString("username"));
        u.setEmail(rs.getString("email"));
        u.setPassword(rs.getString("password"));

        //Tabla uol -> El rol se selecciona en el respectivo servelts
        Rol r = new Rol();
        r.setId(rs.getInt("idRol"));
        r.setNombre(rs.getString("rol"));

        //Uniendo
        u.setRol(r);

        return u;
    }
}
