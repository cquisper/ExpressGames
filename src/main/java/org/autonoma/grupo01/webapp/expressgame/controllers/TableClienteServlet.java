package org.autonoma.grupo01.webapp.expressgame.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.autonoma.grupo01.webapp.expressgame.annotations.Security;
import org.autonoma.grupo01.webapp.expressgame.models.Cliente;
import org.autonoma.grupo01.webapp.expressgame.services.ClienteService;
import org.autonoma.grupo01.webapp.expressgame.services.RegisterLoginService;

import java.io.IOException;
import java.util.List;

@WebServlet({"/tabla/clientes", "/tabla/administradores"})
public class TableClienteServlet extends HttpServlet {
    //servicio
    //! requeririas de un named si no fuera el unic p
    @Inject
    private ClienteService serviceCliente;

    @Inject
    @Security
    private RegisterLoginService registerLoginService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tipo;
        Integer idRol;
        try {
            idRol = Integer.parseInt(req.getParameter("idRol"));
            tipo = idRol == 1 ? "clientes" : idRol == 2 ? "administradores" : "No registrado en el sistema";
        }catch (NumberFormatException e){
            tipo = req.getServletPath().substring(7);
            idRol = getRolParameter(tipo);
        }

        List<Cliente> clientes = serviceCliente.listarPorRol(idRol).stream()
                .map(cliente -> {
                    Cliente c = cliente;
                    c.getUsuario().setPassword(registerLoginService.decrypt(cliente.getUsuario().getPassword()));
                    return c;
                }).toList();

        req.setAttribute("tipo", tipo);

        req.setAttribute("clientes", clientes);

        req.setAttribute("title", "Express Controller" + " - " + "tabla " + tipo);

        this.getServletContext().getRequestDispatcher("/tablecliente.jsp").forward(req,resp);
    }

    private Integer getRolParameter(String tipo) {
        Integer rol;
        switch (tipo){
            case "clientes":
                rol = 1;
                break;
            case "administradores":
                rol = 2;
                break;
            default:
                rol = 0;
                break;
        }
        return rol;
    }
}


