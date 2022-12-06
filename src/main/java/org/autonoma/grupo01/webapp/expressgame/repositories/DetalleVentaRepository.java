package org.autonoma.grupo01.webapp.expressgame.repositories;

import org.autonoma.grupo01.webapp.expressgame.models.DetalleVenta;

import java.sql.SQLException;
import java.util.List;

public interface DetalleVentaRepository extends CrudRepository<DetalleVenta>{

    List<DetalleVenta> listarPorIdVenta(Integer idVenta) throws SQLException;

}
