package org.autonoma.grupo01.webapp.expressgame.repositories;

import jakarta.inject.Inject;
import org.autonoma.grupo01.webapp.expressgame.annotations.MysqlConn;
import org.autonoma.grupo01.webapp.expressgame.annotations.Repository;
import org.autonoma.grupo01.webapp.expressgame.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductoRepositoryImpl implements ProductoRepository{

    private final String SQL_SELECT = "SELECT * FROM vistaproducto";

    private final String SQL_SELECT_BY_CODE = "SELECT * FROM vistaproducto WHERE codigo = ?";

    private final String SQL_SELECT_BY_ID = "SELECT * FROM producto WHERE idProducto = ?";

    private final String SQL_COUNT = "SELECT COUNT(*) FROM producto";

    private final String SQL_LIMIT = "SELECT * FROM vistaproducto LIMIT ?,?";

    private final String SQL_DELETE = "DELETE FROM producto WHERE idProducto = ?";

    @Inject
    @MysqlConn
    private Connection conn;

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_SELECT)) {
            while (rs.next()) {

                Producto p = getProducto(rs);

                productos.add(p);
            }
        }
        return productos;
    }

    @Override
    public Producto porId(Integer id) throws SQLException {
        Producto p = null;
        try(PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_ID)){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    p = new Producto();
                    p.setId(rs.getInt("idProducto"));
                }
            }
        }
        return p;
    }

    @Override
    public Producto guardar(Producto producto) throws SQLException {
        String SQL_UPDATE;
        if(producto.getId() != null && producto.getId() > 0){
            SQL_UPDATE = "UPDATE producto SET idSubCategoria = ?, idPlataforma = ?, nombre = ?, descripcion = ?, especificacion = ?, precio = ?, imagenRuta = ?, codigo = ? WHERE idProducto = ?";
        }else{
            SQL_UPDATE = "INSERT INTO producto (idSubCategoria, idPlataforma, nombre, descripcion, especificacion, precio, imagenRuta, codigo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        }
        try(PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE)){
            stmt.setInt(1, producto.getSubCategoria().getId());
            stmt.setInt(2, producto.getPlataforma().getId());
            stmt.setString(3, producto.getNombre());
            stmt.setString(4, producto.getDescripcion());
            stmt.setString(5, producto.getEspecificacionStr());
            stmt.setDouble(6, producto.getPrecio());
            stmt.setString(7, producto.getImagenRuta());
            stmt.setString(8, producto.getCodigo());

            if(producto.getId() != null && producto.getId() > 0){
                stmt.setInt(9, producto.getId());
            }

            stmt.executeUpdate();
        }
        return producto;
    }

    @Override
    public void eliminar(Integer id) throws SQLException {
        try(PreparedStatement stmt = conn.prepareStatement(SQL_DELETE)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Producto selectByCode(String codigo) throws SQLException {
        Producto p = null;
        try(PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_CODE)){
            stmt.setString(1, codigo);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    p = getProducto(rs);
                }
            }
        }

        return p;
    }

    @Override
    public List<Producto> limit(Integer inicio, Integer cantidad) throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(SQL_LIMIT)) {
            stmt.setInt(1, inicio);
            stmt.setInt(2, cantidad);

            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Producto p = getProducto(rs);
                    productos.add(p);
                }
            }
        }
        return productos;
    }

    @Override
    public List<Producto> limitByTypeField(Integer idTipo, String tipo, Integer inicio, Integer cantidad) throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String SQL_LIMIT_BY_TYPEFIELD = "SELECT * FROM vistaproducto";

        switch (tipo){
            case "cat":
                SQL_LIMIT_BY_TYPEFIELD = SQL_LIMIT_BY_TYPEFIELD + " WHERE idCategoria = ? LIMIT ?,?";
                break;
            case "plat":
                SQL_LIMIT_BY_TYPEFIELD = SQL_LIMIT_BY_TYPEFIELD + " WHERE idPlataforma = ? LIMIT ?,?";
                break;
            case "scat":
                SQL_LIMIT_BY_TYPEFIELD = SQL_LIMIT_BY_TYPEFIELD + " WHERE idSubCategoria = ? LIMIT ?,?";
                break;
        }

        try (PreparedStatement stmt = conn.prepareStatement(SQL_LIMIT_BY_TYPEFIELD)) {

            if(idTipo != 0) {
                stmt.setInt(1, idTipo);
                stmt.setInt(2, inicio);
                stmt.setInt(3, cantidad);
            }

            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Producto p = getProducto(rs);
                    productos.add(p);
                }
            }
        }
        return productos;
    }

    @Override
    public List<Producto> limitBySearch(Integer idCategoria, String busqueda, Integer inicio, Integer cantidad) throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String SQL_LIMIT_BY_SEARCH = "SELECT * FROM vistaproducto";

        if(idCategoria == 0){
            SQL_LIMIT_BY_SEARCH = SQL_LIMIT_BY_SEARCH + " WHERE nombre LIKE ? LIMIT ?,?";
        }else {
            SQL_LIMIT_BY_SEARCH = SQL_LIMIT_BY_SEARCH + " WHERE idCategoria = ? AND nombre LIKE ? LIMIT ?,?";
        }
        try (PreparedStatement stmt = conn.prepareStatement(SQL_LIMIT_BY_SEARCH)) {

            if(idCategoria == 0){
                stmt.setString(1, busqueda);
                stmt.setInt(2, inicio);
                stmt.setInt(3, cantidad);
            }else {
                stmt.setInt(1, idCategoria);
                stmt.setString(2, busqueda);
                stmt.setInt(3, inicio);
                stmt.setInt(4, cantidad);
            }

            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Producto p = getProducto(rs);
                    productos.add(p);
                }
            }
        }
        return productos;
    }

    @Override
    public List<Producto> limitByState(String estado, Integer idCategoria, Integer inicio, Integer cantidad) throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String SQL_LIMIT_BY_STATE= "SELECT * FROM vistaproducto";

        switch (idCategoria){
            case 0:
                SQL_LIMIT_BY_STATE = SQL_LIMIT_BY_STATE + " WHERE estado = ? LIMIT ?,?";
                break;
        }

        try (PreparedStatement stmt = conn.prepareStatement(SQL_LIMIT_BY_STATE)) {
            if(estado != null) {
                stmt.setString(1, estado);
                stmt.setInt(2, inicio);
                stmt.setInt(3, cantidad);
            }

            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Producto p = getProducto(rs);
                    productos.add(p);
                }
            }
        }
        return productos;
    }

    public List<Producto> limitByPrice(Double minimo, Double maximo, Integer inicio, Integer cantidad) throws SQLException {
        List<Producto> productos = new ArrayList<>();

        String SQL_CALL_BY_PRICE= "{CALL min_max_precio_producto(?, ?, ?, ?)}";
        //procedimiento almacenado
        try(CallableStatement cstm= conn.prepareCall(SQL_CALL_BY_PRICE)){
            cstm.setDouble(1, minimo);
            cstm.setDouble(2, maximo);
            cstm.setInt(3, inicio);
            cstm.setInt(4, cantidad);

            try(ResultSet rs = cstm.executeQuery()) {
                while (rs.next()) {
                    Producto p = getProducto(rs);
                    productos.add(p);
                }
            }
        }

        return productos;
    }

    @Override
    public Integer countByPrice(Double minimo, Double maximo) throws SQLException {
        int rows = 0;
        String SQL_CALL_COUNT = "{CALL count_min_max_precio_producto(?, ?, ?)}";

        //procedimiento almacenado
        try(CallableStatement cstm= conn.prepareCall(SQL_CALL_COUNT)){
            cstm.setDouble(1, minimo);
            cstm.setDouble(2, maximo);

            cstm.registerOutParameter(3, Types.INTEGER);

            cstm.execute();

            rows = cstm.getInt(3);
        }

        return rows;
    }

    @Override
    public Integer count() throws SQLException {
        int rows = 0;
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_COUNT)) {
            if (rs.next()) {
                rows = rs.getInt("COUNT(*)");
            }
        }
        return rows;
    }

    @Override
    public Integer countByTypeField(Integer idTipo, String tipo) throws SQLException {
        int rows = 0;
        String SQL_COUNT_BY_CATEGORY = "SELECT COUNT(*) FROM producto AS p";

        switch (tipo){
            case "cat":
                SQL_COUNT_BY_CATEGORY = SQL_COUNT_BY_CATEGORY + " INNER JOIN subcategoria AS sc ON (p.idSubCategoria = sc.idSubCategoria)" +
                        " INNER JOIN categoria AS c ON (sc.idCategoria = c.idCategoria) WHERE c.idCategoria = ?";
                break;
            case "plat":
                SQL_COUNT_BY_CATEGORY = SQL_COUNT_BY_CATEGORY + " WHERE p.idPlataforma = ?";
                break;
            case "scat":
                SQL_COUNT_BY_CATEGORY = SQL_COUNT_BY_CATEGORY + " WHERE p.idSubCategoria = ?";
                break;
        }
        try (PreparedStatement stmt = conn.prepareStatement(SQL_COUNT_BY_CATEGORY)) {
            if(idTipo != 0) {
                stmt.setInt(1, idTipo);
            }
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()) {
                    rows = rs.getInt("COUNT(*)");
                }
            }
        }
        return rows;
    }

    @Override
    public Integer countBySearch(Integer idCategoria, String busqueda) throws SQLException {
        int rows = 0;
        String SQL_COUNT_BY_SEARCH = "SELECT COUNT(*) FROM producto AS p INNER JOIN subcategoria AS sc ON (p.idSubCategoria = sc.idSubCategoria)" +
                " INNER JOIN categoria AS c ON (sc.idCategoria = c.idCategoria)";
        if(idCategoria == 0){
            SQL_COUNT_BY_SEARCH = SQL_COUNT_BY_SEARCH + " WHERE p.nombre LIKE ?";
        }else {
            SQL_COUNT_BY_SEARCH = SQL_COUNT_BY_SEARCH + " WHERE c.idCategoria = ? AND p.nombre LIKE ?";
        }
        try (PreparedStatement stmt = conn.prepareStatement(SQL_COUNT_BY_SEARCH)){
            if(idCategoria == 0){
                stmt.setString(1, busqueda);
            }else {
                stmt.setInt(1, idCategoria);
                stmt.setString(2, busqueda);
            }

            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    rows = rs.getInt("COUNT(*)");
                }
            }
        }
        return rows;
    }

    private Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getInt("idProducto"));

        SubCategoria sc = new SubCategoria();
        sc.setId(rs.getInt("idSubCategoria"));
        sc.setNombre(rs.getString("subcategoria"));

        Categoria c = new Categoria();
        c.setId(rs.getInt("idCategoria"));
        c.setNombre(rs.getString("categoria"));

        sc.setCategoria(c);

        Plataforma pl = new Plataforma();
        pl.setId(rs.getInt("idPlataforma"));
        pl.setNombre(rs.getString("plataforma"));

        Empresa e = new Empresa();
        e.setId(rs.getInt("idEmpresa"));
        e.setNombre(rs.getString("empresa"));

        pl.setEmpresa(e);

        p.setNombre(rs.getString("nombre"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setEspecificacion(rs.getString("especificacion").split("&"));
        p.setPrecio(rs.getDouble("precio"));
        p.setImagenRuta(rs.getString("imagenRuta"));
        p.setCodigo(rs.getString("codigo"));
        p.setEstado(rs.getString("estado"));

        p.setSubCategoria(sc);
        p.setPlataforma(pl);

        return p;
    }

}
