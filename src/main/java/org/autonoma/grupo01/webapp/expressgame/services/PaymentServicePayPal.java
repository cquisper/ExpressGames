package org.autonoma.grupo01.webapp.expressgame.services;

import com.paypal.base.rest.PayPalRESTException;
import jakarta.inject.Inject;
import org.autonoma.grupo01.webapp.expressgame.annotations.Service;
import org.autonoma.grupo01.webapp.expressgame.models.Cliente;
import org.autonoma.grupo01.webapp.expressgame.models.Producto;
import org.autonoma.grupo01.webapp.expressgame.models.Venta;
import org.autonoma.grupo01.webapp.expressgame.repositories.PaymentRepository;

import java.util.Optional;

@Service
public class PaymentServicePayPal implements PaymentService{

    @Inject
    private PaymentRepository paymentRepository;

    @Inject
    private ProductoService productoService;

    @Inject
    private Venta venta;

    @Override
    public String checkoutPaymentPayPal(Cliente cliente) {
        try {
            String paypalLinkPayment = paymentRepository.checkoutPaymentPayPal(cliente);
            configVenta();//con este metodo se arreglo el bug conversion dolar en PaymentRepositoryPayPal
            return paypalLinkPayment;
        } catch (PayPalRESTException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    private void configVenta() {
        venta.getDetalleVentas().forEach(detalleVenta -> {
            Optional<Producto> productoOptional= productoService.selectByCode(detalleVenta.getProducto().getCodigo());
            productoOptional.ifPresent(producto -> detalleVenta.setProducto(producto));
        });
    }
}
