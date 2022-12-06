package org.autonoma.grupo01.webapp.expressgame.repositories;

import org.autonoma.grupo01.webapp.expressgame.models.Venta;

import java.sql.SQLException;
import java.util.List;

public interface VentaRepository extends CrudRepository<Venta>{

    List<Venta> listarPorCliente(Integer idCliente) throws SQLException;

}
