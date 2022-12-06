package org.autonoma.grupo01.webapp.expressgame.services;

import jakarta.inject.Inject;
import org.autonoma.grupo01.webapp.expressgame.annotations.Service;
import org.autonoma.grupo01.webapp.expressgame.models.DetalleVenta;
import org.autonoma.grupo01.webapp.expressgame.models.Venta;
import org.autonoma.grupo01.webapp.expressgame.repositories.DetalleVentaRepository;
import org.autonoma.grupo01.webapp.expressgame.repositories.VentaRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImpl implements VentaService{

    @Inject
    private VentaRepository ventaRepository;

    @Inject
    private DetalleVentaRepository detalleVentaRepository;

    @Override
    public List<Venta> listarPorCliente(Integer idCliente) {
        try {
            return ventaRepository.listarPorCliente(idCliente);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Venta> porId(Integer id) {
        try {
            return Optional.ofNullable(ventaRepository.porId(id));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Venta registroVenta(Venta venta) {
        Venta ventaInsert;
        try {
            ventaInsert = ventaRepository.guardar(venta);

            for (DetalleVenta detalleVenta : ventaInsert.getDetalleVentas()) {
                detalleVenta.setIdVenta(venta.getId());
                detalleVentaRepository.guardar(detalleVenta);
            }
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
        return ventaInsert;
    }

    @Override
    public List<DetalleVenta> listarDetVentaPorIdVenta(Integer idVenta) {
        try {
            return detalleVentaRepository.listarPorIdVenta(idVenta);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
