package org.autonoma.grupo01.webapp.expressgame.repositories;

import java.sql.SQLException;
import java.util.List;

public interface CrudRepository<T> {
    List<T> listar() throws SQLException;

    T porId(Integer id) throws SQLException;

    T guardar(T t) throws  SQLException;

    void eliminar(Integer id) throws SQLException;

}
