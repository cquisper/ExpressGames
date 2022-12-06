package org.autonoma.grupo01.webapp.expressgame.repositories;

import jakarta.inject.Inject;
import org.autonoma.grupo01.webapp.expressgame.annotations.MysqlConn;
import org.autonoma.grupo01.webapp.expressgame.annotations.Repository;
import org.autonoma.grupo01.webapp.expressgame.models.Empresa;
import org.autonoma.grupo01.webapp.expressgame.models.Plataforma;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PlataformaRepository implements CrudRepository<Plataforma>{

    private final String SQL_SELECT = "SELECT * FROM plataforma;";
    @Inject
    @MysqlConn
    private Connection conn;

    @Override
    public List<Plataforma> listar() throws SQLException {
        List<Plataforma> plataformas = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(SQL_SELECT);
             ResultSet rs = stmt.executeQuery()){
            while (rs.next()) {
                Plataforma pl = new Plataforma();
                pl.setId(rs.getInt("idPlataforma"));
                pl.setNombre(rs.getString("nombre"));

                Empresa e = new Empresa();
                e.setId(rs.getInt("idEmpresa"));

                pl.setEmpresa(e);

                plataformas.add(pl);
            }

        }
        return plataformas;
    }

    @Override
    public Plataforma porId(Integer id) throws SQLException {
        return null;
    }

    @Override
    public Plataforma guardar(Plataforma plataforma) throws SQLException {
        return null;
    }

    @Override
    public void eliminar(Integer id) throws SQLException {

    }
}
