package org.autonoma.grupo01.webapp.expressgame.repositories;

import com.paypal.base.rest.PayPalRESTException;
import org.autonoma.grupo01.webapp.expressgame.models.Cliente;

public interface PaymentRepository {

    String checkoutPaymentPayPal(Cliente cliente) throws PayPalRESTException;

}
