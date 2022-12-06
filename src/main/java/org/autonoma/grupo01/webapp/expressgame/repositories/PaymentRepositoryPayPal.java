package org.autonoma.grupo01.webapp.expressgame.repositories;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import jakarta.inject.Inject;
import org.autonoma.grupo01.webapp.expressgame.annotations.Repository;
import org.autonoma.grupo01.webapp.expressgame.models.Cliente;
import org.autonoma.grupo01.webapp.expressgame.models.DetalleVenta;
import org.autonoma.grupo01.webapp.expressgame.models.Producto;
import org.autonoma.grupo01.webapp.expressgame.models.Venta;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepositoryPayPal implements PaymentRepository{

    private static final String CLIENT_ID = "Af6c-vjPrv6ilLuNowhko8evz1alcYWiEGT0xXX06eCHeFfvMAFK4XA4HmgpzEcxuCrmfvSPiWDvQ0cD";
    private static final String CLIENT_SECRET = "EA8ANY--JVkwPYAUbtVVgpaRJCBYNNOeCIZnCUC5BaQ5Ao-BLgen29YYgazCc8JXmsIw_KYkHJgZG1-Z";
    private static final String MODE = "sandbox";

    @Inject
    private Venta venta;

    @Override
    public String checkoutPaymentPayPal(Cliente cliente) throws PayPalRESTException {
        Payer payerCliente = getClienteInfo(cliente);

        RedirectUrls redirectUrls = getRedirectUrls();

        List<Transaction> transacciones = getTransacciones();

        Payment requestPayment = new Payment();
        requestPayment.setTransactions(transacciones);
        requestPayment.setRedirectUrls(redirectUrls);
        requestPayment.setPayer(payerCliente);
        requestPayment.setIntent("order");

        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

        Payment aprovedPayment = requestPayment.create(apiContext);

        return getApprovalLink(aprovedPayment);
    }

    private String getApprovalLink(Payment approvedPayment){
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;
        for (Links linksItem : links) {
            if (linksItem.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = linksItem.getHref();
                break;
            }
        }

        return approvalLink;
    }

    private List<Transaction> getTransacciones(){
        ItemList carritoCompra = new ItemList(); //aqui se guarda la lista del carrito de compras
        List<Item> carritoCompraList = new ArrayList<>();

        Venta ventaUSD = getVentaUSD();

        ventaUSD.getDetalleVentas().forEach(detalleVenta -> {
            Item itemCarrito = new Item();
            itemCarrito.setCurrency("USD");
            itemCarrito.setQuantity(String.valueOf(detalleVenta.getCantidad()));
            itemCarrito.setName(detalleVenta.getProducto().getNombre());
            itemCarrito.setPrice(String.valueOf(detalleVenta.getProducto().getPrecio()));

            carritoCompraList.add(itemCarrito);
        });

        carritoCompra.setItems(carritoCompraList);

        Transaction transaccion = new Transaction();
        transaccion.setItemList(carritoCompra);

        Details detalle = new Details();
        detalle.setShipping("0"); // Costo de envio 0
        detalle.setSubtotal(String.valueOf(Math.round(ventaUSD.getMonto()*100.0)/100.0));

        Amount monto = new Amount();//monto
        monto.setCurrency("USD");
        monto.setTotal(String.valueOf(Math.round(ventaUSD.getMonto()*100.0)/100.0));
        monto.setDetails(detalle);

        transaccion.setAmount(monto);
        transaccion.setDescription("Tu carrito de compras");

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaccion);

        return transactionList;
    }

    private Payer getClienteInfo(Cliente cliente) {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName(cliente.getNombre());
        payerInfo.setLastName(cliente.getApellido());
        payerInfo.setEmail(cliente.getUsuario().getEmail());

        Address direccionEnvio = new Address();
        String[] direccionArray = venta.getDireccionEntrega().split(", ");
        direccionEnvio.setCountryCode("PE");
        direccionEnvio.setLine1(direccionArray[0]);
        direccionEnvio.setCity(direccionArray[1]);
        direccionEnvio.setState(direccionArray[2]);

        payerInfo.setBillingAddress(direccionEnvio);
        payerInfo.setCountryCode("+51");

        payer.setPayerInfo(payerInfo);

        return payer;
    }

    private RedirectUrls getRedirectUrls(){
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8080/webapp.express-game/payment/cancel");
        redirectUrls.setReturnUrl("http://localhost:8080/webapp.express-game/payment/process");

        return redirectUrls;
    }

    private Venta getVentaUSD(){
        Venta ventaConv = new Venta();
        //BUG-> El precio de los productos se modifica lo cual es un problema. Pero ya se arreglo en PaymentServicePayPaL
        ventaConv.setDetalleVentas(new ArrayList<>(venta.getDetalleVentas()));
        List<DetalleVenta> detalleVentasConv = ventaConv.getDetalleVentas().stream().map(detalleVenta -> {
            DetalleVenta detVentaConv = new DetalleVenta();
            Producto pConv = detalleVenta.getProducto();
            Double precioConv = Math.round((pConv.getPrecio() / 3.83)*100.0)/100.0; //convierte el precio q esta en soles a dolares redondeando a dos decimales
            pConv.setPrecio(precioConv);
            detVentaConv.setCantidad(detalleVenta.getCantidad());
            detVentaConv.setProducto(pConv);

            return detVentaConv;
        }).peek(detalleVenta -> System.out.printf("cantidad: "+detalleVenta.getCantidad()+" - precio: "+detalleVenta.getProducto().getPrecio()+" - Importe: "+detalleVenta.getImporte())).toList();

        System.out.println("monto: "+ventaConv.getMonto());
        ventaConv.setDetalleVentas(detalleVentasConv);

        return ventaConv;
    }
}
