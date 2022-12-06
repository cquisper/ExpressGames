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
import org.autonoma.grupo01.webapp.expressgame.services.RegisterLoginService;
import org.autonoma.grupo01.webapp.expressgame.services.VentaService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet("/login")
public class SingInServlet extends HttpServlet {

    @Inject
    private VentaService ventaService;

    @Inject
    private ClienteService clienteService;

    @Inject
    @Security
    private RegisterLoginService registerLoginService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String> dataPaso = registerLoginService.getDataPaso(false);

        req.setAttribute("dataPaso", dataPaso);

        req.setAttribute("title", "Login - " + req.getAttribute("title"));
        this.getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usernameOrEmail = req.getParameter("usario_email");
        String password = registerLoginService.encrypt(req.getParameter("password-login"));
        boolean loginModal = req.getParameter("loginModal") != null && req.getParameter("loginModal") != "" ? Boolean.valueOf(req.getParameter("loginModal")) : false;

        Optional<Usuario> usuarioOptional = clienteService.login(usernameOrEmail, password);
        //El usuario esta presente es decir si devolvio al usuario correcto
        if(usuarioOptional.isPresent()){
            //Decido guardarlo como un optional para que las validaciones en los jsp sean mas rapidas
            req.getSession().setAttribute("usuario", usuarioOptional);
            //Obteniendo el historial de compra del usuario

            Optional<Cliente> clienteOptional = clienteService.porIdUsuario(usuarioOptional.get().getId());
            if (clienteOptional.isPresent()) {
                List<Venta> historialCompra = ventaService.listarPorCliente(clienteOptional.get().getId());
                req.getSession().setAttribute("historialCompra", historialCompra);
            }

            if(loginModal){
                this.getServletContext().getRequestDispatcher("/carrito").forward(req, resp);
            }else {
                resp.sendRedirect(req.getContextPath() + "/express.game");
            }
        }else{
            //Quiere decir que no coindiden el username,email o contraseña
            //this.getServletContext().getRequestDispatcher( "/login").forward(req, resp);

            //verificando si viene del modal
            if(loginModal){ // viene del modal el inicio de sesion y por ende ingreso sus datos mal desde ese formulario
                req.getSession().setAttribute("modalCompra", "true");
                this.getServletContext().getRequestDispatcher("/carrito").forward(req, resp);
            }else{//viene de la pag login
                doGet(req, resp);
            }
        }
    }
}
