package org.autonoma.grupo01.webapp.expressgame.services;


import org.autonoma.grupo01.webapp.expressgame.models.Plataforma;
import org.autonoma.grupo01.webapp.expressgame.models.Producto;
import org.autonoma.grupo01.webapp.expressgame.models.SubCategoria;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProductoService {

    void guardarProducto(Producto producto);

    void eliminarProducto(Integer idProducto);

    Optional<Producto> porIdProducto(Integer idProducto);

    List<SubCategoria> listarSubCategoriaporCategoria(Integer idCategoria);

    List<Plataforma> listarPlataforma();

    Optional<Producto> selectByCode(String codigo);

    List<Producto> limit(Integer inicio, Integer cantidad);

    List<Producto> limitByTypeField(Integer idTipo, String tipo, Integer inicio, Integer cantidad);

    List<Producto> limitBySearch(Integer idCategoria, String busqueda, Integer inicio, Integer cantidad);

    List<Producto> limitByState(String estado, Integer idCategoria, Integer inicio, Integer cantidad);

    List<Producto> limitByPrice(Double minimo, Double maximo, Integer inicio, Integer cantidad);

    Integer countByPrice(Double minimo, Double maximo);

    Integer count();

    Integer countByTypeField(Integer idTipo, String tipo);

    Integer countBySearch(Integer idCategoria, String busqueda);
}
