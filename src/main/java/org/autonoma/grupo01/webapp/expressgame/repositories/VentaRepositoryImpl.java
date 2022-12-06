package org.autonoma.grupo01.webapp.expressgame.repositories;

import jakarta.inject.Inject;
import org.autonoma.grupo01.webapp.expressgame.annotations.MysqlConn;
import org.autonoma.grupo01.webapp.expressgame.annotations.Repository;
import org.autonoma.grupo01.webapp.expressgame.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VentaRepositoryImpl implements VentaRepository{

    private final String SQL_SELECT_BY_ID = "SELECT * FROM vistaventa WHERE idVenta = ?";
    private final String SQL_SELECT_BY_CLIENT = "SELECT v.*, p.nombre AS nombre_producto FROM venta AS v INNER JOIN detventa AS dtv ON (v.idVenta = dtv.idVenta) INNER JOIN producto AS p ON (dtv.idProducto = p.idProducto) WHERE v.idCliente = ?";
    private final String SQL_INSERT = "INSERT INTO venta(idCliente, idDistribuidor, monto, fecha, direccionEntrega) VALUES (?, ?, ?, ?, ?)";

    @Inject
    @MysqlConn
    private Connection conn;

    @Override
    public List<Venta> listar() throws SQLException {
        return null;
    }

    @Override
    public List<Venta> listarPorCliente(Integer idCliente) throws SQLException {
        List<Venta> ventas = new ArrayList<>();

        try(PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_CLIENT)){
            stmt.setInt(1, idCliente);

            try(ResultSet rs = stmt.executeQuery()){

                while (rs.next()){

                    Venta venta = new Venta();
                    venta.setId(rs.getInt("idVenta"));

                    Producto producto = new Producto();
                    producto.setNombre(rs.getString("nombre_producto"));

                    DetalleVenta detalleVenta = new DetalleVenta();
                    detalleVenta.setProducto(producto);

                    venta.setDetalleVenta(detalleVenta);
                    venta.setMonto(rs.getDouble("monto"));
                    venta.setFecha(rs.getDate("fecha").toLocalDate());

                    ventas.add(venta);
                }
            }
        }

        return ventas;
    }

    @Override
    public Venta porId(Integer id) throws SQLException {
        Venta venta = null;

        try (PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_ID)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    venta = getVenta(rs);
                }
            }
        }

        return venta;
    }

    @Override
    public Venta guardar(Venta venta) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(SQL_INSERT,  Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, venta.getCliente().getId());
            stmt.setInt(2, venta.getDistribuidor().getId());
            stmt.setDouble(3, venta.getMonto());
            stmt.setDate(4, Date.valueOf(venta.getFecha()));
            stmt.setString(5, venta.getDireccionEntrega());

            stmt.executeUpdate();//Guardadndo los datos

            //Obtenemos el id generado
            try(ResultSet rs = stmt.getGeneratedKeys()){
                if(rs.next()){
                    venta.setId(rs.getInt(1));
                }
            }
        }

        return venta;
    }

    @Override
    public void eliminar(Integer id) throws SQLException {

    }

    private Venta getVenta(ResultSet rs) throws SQLException {
        Venta v = new Venta();
        v.setId(rs.getInt("idVenta"));

        Cliente c = new Cliente();
        c.setId(rs.getInt("idCliente"));
        c.setNombre(rs.getString("cnombre"));
        c.setApellido(rs.getString("capellido"));
        c.setDni(rs.getString("cdni"));

        v.setCliente(c);

        Distribuidor d = new Distribuidor();
        d.setId(rs.getInt("idDistribuidor"));
        d.setNombre(rs.getString("dnombre"));
        d.setApellido(rs.getString("dapellido"));
        d.setDni(rs.getString("ddni"));
        d.setTelefono(rs.getString("dtelefono"));

        v.setDistribuidor(d);

        v.setMonto(rs.getDouble("monto"));
        v.setFecha(rs.getDate("fecha").toLocalDate());
        v.setDireccionEntrega(rs.getString("direccionEntrega"));

        return v;
    }
}
