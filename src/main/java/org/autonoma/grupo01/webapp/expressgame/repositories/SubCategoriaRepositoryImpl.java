package org.autonoma.grupo01.webapp.expressgame.repositories;

import jakarta.inject.Inject;
import org.autonoma.grupo01.webapp.expressgame.annotations.MysqlConn;
import org.autonoma.grupo01.webapp.expressgame.annotations.Repository;
import org.autonoma.grupo01.webapp.expressgame.models.Categoria;
import org.autonoma.grupo01.webapp.expressgame.models.SubCategoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SubCategoriaRepositoryImpl implements SubCategoriaRepository{

    private final String SQL_SELECT_BY_CATEGORY = "SELECT sc.*, c.nombre AS categoria FROM subcategoria AS sc INNER JOIN categoria AS c ON (sc.idCategoria = c.idCategoria)" +
            " WHERE c.idCategoria = ?";

    private final String SQL_SELECT_BY_ID = "SELECT sc.*, c.nombre AS categoria FROM subcategoria AS sc INNER JOIN categoria AS c ON (sc.idCategoria = c.idCategoria)" +
            " WHERE sc.idSubCategoria = ?";

    @Inject
    @MysqlConn
    private Connection conn;

    @Override
    public List<SubCategoria> listarPorCategoria(Integer idCategoria) throws SQLException {
        List<SubCategoria> subCategorias = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_CATEGORY)){
            stmt.setInt(1, idCategoria);

            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    SubCategoria sc = getSubCategoria(rs);
                    subCategorias.add(sc);
                }
            }
        }
        return subCategorias;
    }

    @Override
    public List<SubCategoria> listar() throws SQLException {
        List<SubCategoria> subCategorias = new ArrayList<>();
        return subCategorias;
    }

    @Override
    public SubCategoria porId(Integer id) throws SQLException {
        SubCategoria sc = null;
        try(PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_ID)){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()) {
                    sc = getSubCategoria(rs);
                }
            }
        }
        return sc;
    }

    @Override
    public SubCategoria guardar(SubCategoria subCategoria) throws SQLException {
        return null;
    }

    @Override
    public void eliminar(Integer id) throws SQLException {

    }

    private SubCategoria getSubCategoria(ResultSet rs) throws SQLException {
        SubCategoria sc = new SubCategoria();
        sc.setId(rs.getInt("idSubCategoria"));
        sc.setNombre(rs.getString("nombre"));

        Categoria c = new Categoria();
        c.setId(rs.getInt("idCategoria"));
        c.setNombre(rs.getString("categoria"));

        sc.setCategoria(c);
        return sc;
    }
}
