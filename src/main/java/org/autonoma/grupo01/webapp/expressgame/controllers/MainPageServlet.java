package org.autonoma.grupo01.webapp.expressgame.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.autonoma.grupo01.webapp.expressgame.annotations.Security;
import org.autonoma.grupo01.webapp.expressgame.models.Cliente;
import org.autonoma.grupo01.webapp.expressgame.models.Producto;
import org.autonoma.grupo01.webapp.expressgame.models.Usuario;
import org.autonoma.grupo01.webapp.expressgame.models.Venta;
import org.autonoma.grupo01.webapp.expressgame.services.ClienteService;
import org.autonoma.grupo01.webapp.expressgame.services.ProductoService;
import org.autonoma.grupo01.webapp.expressgame.services.RegisterLoginService;
import org.autonoma.grupo01.webapp.expressgame.services.VentaService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet({"/express.game", "/express.controller"})
public class MainPageServlet extends HttpServlet {

    @Inject
    private ProductoService productoService;

    @Inject
    @Security
    private RegisterLoginService registerLoginService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pageMain = req.getServletPath().substring(req.getContextPath().indexOf("/"));

        Optional<Usuario> usuarioOptional = registerLoginService.getUser(req);

        String rol = usuarioOptional.isPresent() ? usuarioOptional.get().getRol().getNombre() : "";

        if(pageMain.contains("controller") && rol.equals("administrador")){
            req.setAttribute("title", "Express Controller");
            this.getServletContext().getRequestDispatcher("/controller.jsp").forward(req, resp);
        }else{
            List<Producto> novedades = productoService.limitByState("novedad", 0, 0, 6);
            List<Producto> promociones = productoService.limitByState("promocion",0,0,6);

            req.setAttribute("novedades1", novedades.subList(0,3));
            req.setAttribute("novedades2", novedades.subList(3,6));

            req.setAttribute("promociones1", promociones.subList(0,3));
            req.setAttribute("promociones2", promociones.subList(3,6));

            this.getServletContext().getRequestDispatcher("/principal.jsp").forward(req, resp);
        }

    }
}
