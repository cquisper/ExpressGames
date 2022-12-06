package org.autonoma.grupo01.webapp.expressgame.repositories;

import org.autonoma.grupo01.webapp.expressgame.models.SubCategoria;

import java.sql.SQLException;
import java.util.List;

public interface SubCategoriaRepository extends CrudRepository<SubCategoria>{

    List<SubCategoria> listarPorCategoria(Integer idCategoria) throws SQLException;

}
