package org.autonoma.grupo01.webapp.expressgame.repositories;

import org.autonoma.grupo01.webapp.expressgame.models.Producto;

import java.sql.SQLException;
import java.util.List;

public interface ProductoRepository extends CrudRepository<Producto>{

    Producto selectByCode(String codigo) throws SQLException;

    List<Producto> limit(Integer inicio, Integer cantidad) throws SQLException;

    List<Producto> limitByTypeField(Integer idTipo, String tipo, Integer inicio, Integer cantidad) throws SQLException;

    List<Producto> limitBySearch(Integer idCategoria, String busqueda, Integer inicio, Integer cantidad) throws SQLException;

    List<Producto> limitByState(String estado, Integer idCategoria, Integer inicio, Integer cantidad) throws SQLException;

    List<Producto> limitByPrice(Double minimo, Double maximo, Integer inicio, Integer cantidad) throws SQLException;

    Integer countByPrice(Double minimo, Double maximo) throws SQLException;

    Integer count() throws SQLException;

    Integer countByTypeField(Integer idTipo, String tipo) throws SQLException;

    Integer countBySearch(Integer idCategoria, String busqueda) throws SQLException;
}
