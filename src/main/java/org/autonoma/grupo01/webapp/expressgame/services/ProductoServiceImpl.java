package org.autonoma.grupo01.webapp.expressgame.services;

import jakarta.inject.Inject;
import org.autonoma.grupo01.webapp.expressgame.annotations.Service;
import org.autonoma.grupo01.webapp.expressgame.models.Plataforma;
import org.autonoma.grupo01.webapp.expressgame.models.Producto;
import org.autonoma.grupo01.webapp.expressgame.models.SubCategoria;
import org.autonoma.grupo01.webapp.expressgame.repositories.CrudRepository;
import org.autonoma.grupo01.webapp.expressgame.repositories.ProductoRepository;
import org.autonoma.grupo01.webapp.expressgame.repositories.SubCategoriaRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Inject
    private ProductoRepository productoRepository;

    @Inject
    private SubCategoriaRepository subCategoriaRepository;

    @Inject
    private CrudRepository<Plataforma> plataformaRepository;

    @Override
    public void guardarProducto(Producto producto) {
        try {
            productoRepository.guardar(producto);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void eliminarProducto(Integer idProducto) {
        try {
            productoRepository.eliminar(idProducto);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Producto> porIdProducto(Integer idProducto) {
        try {
            return Optional.ofNullable(productoRepository.porId(idProducto));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<SubCategoria> listarSubCategoriaporCategoria(Integer idCategoria) {
        try {
            return subCategoriaRepository.listarPorCategoria(idCategoria);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Plataforma> listarPlataforma() {
        try {
            return plataformaRepository.listar();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Producto> selectByCode(String codigo) {
        try {
            return Optional.ofNullable(productoRepository.selectByCode(codigo));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Producto> limit(Integer inicio, Integer cantidad) {
        try {
            return productoRepository.limit(inicio, cantidad);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Producto> limitByTypeField(Integer idTipo, String tipo, Integer inicio, Integer cantidad) {
        try {
            return productoRepository.limitByTypeField(idTipo, tipo, inicio, cantidad);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Producto> limitBySearch(Integer idCategoria, String busqueda, Integer inicio, Integer cantidad) {
        busqueda = (busqueda != null) ? ("%"+busqueda+"%").replaceAll(" ", "%") : "%%";
        try {
            return productoRepository.limitBySearch(idCategoria, busqueda, inicio, cantidad);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Producto> limitByState(String estado, Integer idCategoria, Integer inicio, Integer cantidad) {
        try {
            return productoRepository.limitByState(estado, idCategoria, inicio, cantidad);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Producto> limitByPrice(Double minimo, Double maximo, Integer inicio, Integer cantidad) {
        try {
            return productoRepository.limitByPrice(minimo, maximo, inicio, cantidad);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Integer countByPrice(Double minimo, Double maximo) {
        try {
            return productoRepository.countByPrice(minimo, maximo);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Integer count() {
        try {
            return productoRepository.count();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Integer countByTypeField(Integer idTipo, String tipo) {
        try {
            return productoRepository.countByTypeField(idTipo, tipo);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Integer countBySearch(Integer idCategoria, String busqueda) {
        busqueda = (busqueda != null) ? ("%"+busqueda+"%").replaceAll(" ", "%") : "%%";
        try {
            return productoRepository.countBySearch(idCategoria, busqueda);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
