package org.autonoma.grupo01.webapp.expressgame.repositories;

import org.autonoma.grupo01.webapp.expressgame.models.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface ClienteRepository extends CrudRepository<Cliente>{

    List<Cliente> listarPorRol(Integer idRol) throws SQLException;

    Cliente porIdUsuario(Integer id) throws SQLException;

}

