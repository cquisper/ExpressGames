package org.autonoma.grupo01.webapp.expressgame.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.autonoma.grupo01.webapp.expressgame.annotations.Security;
import org.autonoma.grupo01.webapp.expressgame.models.Cliente;
import org.autonoma.grupo01.webapp.expressgame.models.Distribuidor;
import org.autonoma.grupo01.webapp.expressgame.models.Usuario;
import org.autonoma.grupo01.webapp.expressgame.models.Venta;
import org.autonoma.grupo01.webapp.expressgame.services.ClienteService;
import org.autonoma.grupo01.webapp.expressgame.services.RegisterLoginService;
import org.autonoma.grupo01.webapp.expressgame.services.VentaService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@WebServlet({"/payment/process", "/payment/cancel"})
public class SalePaymentServlet extends HttpServlet {
    @Inject
    private VentaService ventaService;
    @Inject
    private ClienteService clienteService;
    @Inject
    @Security
    private RegisterLoginService registerLoginService;
    @Inject
    private Venta venta;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String payment = req.getServletPath().substring(9);
        if(payment.contains("?")){
            payment = payment.substring(0, payment.indexOf("?"));
        }

        switch (payment){
            case "cancel":
                this.getServletContext().getRequestDispatcher("/carrito").forward(req, resp);
                break;
            case "process":
                Optional<Cliente> clienteOptional = registerLoginService.getCliente(req);

                if(clienteOptional.isPresent()){
                    venta.setCliente(clienteOptional.get());
                    Distribuidor distribuidor = new Distribuidor();
                    distribuidor.setId(1); //segun el id insertados los distribuidores
                    venta.setDistribuidor(distribuidor);
                    venta.setFecha(LocalDate.now());

                    Venta ventaRealizada = ventaService.registroVenta(venta);

                    //Unica forma de resetear todo
                    Optional<Usuario> usuarioOptional = registerLoginService.getUser(req);

                    if(usuarioOptional.isPresent()){
                        req.getSession().invalidate();

                        req.getSession().setAttribute("usuario", usuarioOptional);

                        //Obteniendo el historial de compra del usuario

                        Optional<Cliente> optionalCliente = clienteService.porIdUsuario(usuarioOptional.get().getId());
                        if (optionalCliente.isPresent()) {
                            List<Venta> historialCompra = ventaService.listarPorCliente(optionalCliente.get().getId());
                            req.getSession().setAttribute("historialCompra", historialCompra);
                        }

                    }

                    this.getServletContext().getRequestDispatcher("/view/boleta?idVenta="+ventaRealizada.getId()).forward(req, resp);
                }

                break;
        }
    }
}
