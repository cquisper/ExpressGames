package org.autonoma.grupo01.webapp.expressgame.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.autonoma.grupo01.webapp.expressgame.annotations.Security;
import org.autonoma.grupo01.webapp.expressgame.models.Cliente;
import org.autonoma.grupo01.webapp.expressgame.models.Usuario;
import org.autonoma.grupo01.webapp.expressgame.models.Venta;
import org.autonoma.grupo01.webapp.expressgame.services.ClienteService;
import org.autonoma.grupo01.webapp.expressgame.services.PaymentService;
import org.autonoma.grupo01.webapp.expressgame.services.RegisterLoginService;

import java.io.IOException;
import java.util.Optional;

@WebServlet({"/checkout/compra", "/checkout/direccion", "/checkout/payment"})
public class CheckoutOrderServlet extends HttpServlet {
    @Inject
    @Security
    private RegisterLoginService registerLoginService;

    @Inject
    private ClienteService clienteService;

    @Inject
    private PaymentService paymentService;

    @Inject
    private Venta venta;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("cambiarDireccion", "true");
        this.getServletContext().getRequestDispatcher("/compra.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String checkout = req.getServletPath().substring(10);

        switch (checkout){
            case "compra":
                Optional<Usuario> usuarioOptional = registerLoginService.getUser(req);
                if(usuarioOptional.isPresent()){
                    Optional<Cliente> clienteOptional = clienteService.porIdUsuario(usuarioOptional.get().getId());
                    req.getSession().setAttribute("cliente", clienteOptional);
                    resp.sendRedirect(req.getContextPath() + "/compra.jsp");
                }else{
                    req.getSession().setAttribute("modalCompra", "true");
                    this.getServletContext().getRequestDispatcher("/carrito").forward(req, resp);
                }
                break;
            case "direccion":
                String departamento = req.getParameter("departamento");
                String provincia = req.getParameter("provincia");
                String distrito = req.getParameter("distrito");
                String calle = req.getParameter("calle");

                String direccionEntrega = calle.concat(", ") + distrito.concat(", ") + provincia.concat(", ") + departamento;
                venta.setDireccionEntrega(direccionEntrega);
                resp.sendRedirect(req.getContextPath() + "/compra.jsp");
                break;
            case "payment":
                Optional<Cliente> clienteOptional = registerLoginService.getCliente(req);
                if(clienteOptional.isPresent()){
                    String paypalLinkPayment = paymentService.checkoutPaymentPayPal(clienteOptional.get());

                    req.getSession().removeAttribute("venta");
                    resp.sendRedirect(paypalLinkPayment);
                }else{
                    resp.sendRedirect(req.getContextPath() + "/express.game");
                }
                break;
        }
    }
}
