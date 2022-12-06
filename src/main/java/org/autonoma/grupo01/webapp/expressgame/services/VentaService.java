package org.autonoma.grupo01.webapp.expressgame.services;

import org.autonoma.grupo01.webapp.expressgame.models.DetalleVenta;
import org.autonoma.grupo01.webapp.expressgame.models.Venta;

import java.util.List;
import java.util.Optional;

public interface VentaService {

    List<Venta> listarPorCliente(Integer idCliente);

    Optional<Venta> porId(Integer id);

    Venta registroVenta(Venta venta);

    List<DetalleVenta> listarDetVentaPorIdVenta(Integer idVenta);

}
