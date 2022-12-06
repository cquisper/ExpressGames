package org.autonoma.grupo01.webapp.expressgame.repositories;

import jakarta.inject.Inject;
import org.autonoma.grupo01.webapp.expressgame.annotations.MysqlConn;
import org.autonoma.grupo01.webapp.expressgame.annotations.Repository;
import org.autonoma.grupo01.webapp.expressgame.models.DetalleVenta;
import org.autonoma.grupo01.webapp.expressgame.models.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DetalleVentaRepositoryImpl implements DetalleVentaRepository{

    private final String SQL_SELECT_BY_VENTA = "SELECT dv.*, p.nombre AS nombre_producto FROM detventa AS dv INNER JOIN producto AS p ON (dv.idProducto = p.idProducto) WHERE dv.idVenta = ?";

    private final String SQL_INSERT = "INSERT INTO detventa(idVenta, idProducto, cantidad, precio, importe) VALUES (?, ?, ?, ?, ?)";

    @Inject
    @MysqlConn
    private Connection conn;

    @Override
    public List<DetalleVenta> listarPorIdVenta(Integer idVenta) throws SQLException {
        List<DetalleVenta> detalleVentas = new ArrayList<>();

        try(PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_VENTA)){
            stmt.setInt(1, idVenta);

            try(ResultSet rs = stmt.executeQuery()){

                while (rs.next()){

                    DetalleVenta dtVenta = getDetalleVenta(rs);

                    detalleVentas.add(dtVenta);
                }
            }
        }

        return detalleVentas;
    }

    private DetalleVenta getDetalleVenta(ResultSet rs) throws SQLException {
        DetalleVenta dtVenta = new DetalleVenta();
        dtVenta.setId(rs.getInt("idDetVenta"));
        dtVenta.setIdVenta(rs.getInt("idVenta"));

        Producto p = new Producto();
        p.setNombre(rs.getString("nombre_producto"));

        dtVenta.setProducto(p);
        dtVenta.setCantidad(rs.getInt("cantidad"));
        dtVenta.setPrecio(rs.getDouble("precio"));
        dtVenta.setImporte(rs.getDouble("importe"));

        return dtVenta;
    }

    @Override
    public List<DetalleVenta> listar() throws SQLException {
        return null;
    }

    @Override
    public DetalleVenta porId(Integer id) throws SQLException {
        return null;
    }

    @Override
    public DetalleVenta guardar(DetalleVenta detalleVenta) throws SQLException {
        try(PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)){
            stmt.setInt(1, detalleVenta.getIdVenta());
            stmt.setInt(2, detalleVenta.getProducto().getId());
            stmt.setInt(3, detalleVenta.getCantidad());
            stmt.setDouble(4, detalleVenta.getProducto().getPrecio());
            stmt.setDouble(5, detalleVenta.getImporte());

            stmt.executeUpdate(); //Guardadndo el registro de la detalle venta
        }

        return detalleVenta;
    }

    @Override
    public void eliminar(Integer id) throws SQLException {

    }
}
