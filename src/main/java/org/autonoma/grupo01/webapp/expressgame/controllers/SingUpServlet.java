package org.autonoma.grupo01.webapp.expressgame.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.autonoma.grupo01.webapp.expressgame.annotations.Security;
import org.autonoma.grupo01.webapp.expressgame.models.Cliente;
import org.autonoma.grupo01.webapp.expressgame.models.Rol;
import org.autonoma.grupo01.webapp.expressgame.models.Usuario;
import org.autonoma.grupo01.webapp.expressgame.services.ClienteService;
import org.autonoma.grupo01.webapp.expressgame.services.RegisterLoginService;

import java.io.IOException;
import java.util.Map;

@WebServlet("/register")
public class SingUpServlet extends HttpServlet {

    @Inject
    private ClienteService clienteService;

    @Inject
    @Security
    private RegisterLoginService registerLoginService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String> dataPaso = registerLoginService.getDataPaso(true);

        req.setAttribute("dataPaso", dataPaso);

        req.setAttribute("title", "Nuevo Registro - " + req.getAttribute("title"));

        this.getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Los siguientes campos ya han sido validados con js para evitar errores

        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String dni = req.getParameter("dni");

        String username = req.getParameter("username");
        String email = req.getParameter("email");

        //Encriptando: Se guarda en la bd encriptado
        String password = registerLoginService.encrypt(req.getParameter("password-register"));
        /*
        Roles: esta en duda aumentar mas roles
        1 -> Usuario
         */

        Usuario usuario = new Usuario(new Rol(1), username, email, password);

        Cliente cliente = new Cliente(nombre, apellido, dni);

        //Registrando el cliente con su usuaario
        clienteService.guardarCliente(cliente, usuario);

        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
