package org.autonoma.grupo01.webapp.expressgame.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.autonoma.grupo01.webapp.expressgame.models.Producto;
import org.autonoma.grupo01.webapp.expressgame.services.ProductoService;

import java.io.IOException;
import java.util.List;

@WebServlet({"/tabla/videojuegos", "/tabla/consolas", "/tabla/mandos"})
public class TableProductServlet extends HttpServlet {
    @Inject
    private ProductoService productoService;
    Integer idCategoria;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tipo;
        try {
            idCategoria = Integer.parseInt(req.getParameter("cat"));
            tipo = idCategoria == 1 ? "videojuegos" : idCategoria == 2 ? "consolas" : "mandos";
        }catch (NumberFormatException e){
            tipo = req.getServletPath().substring(7);
            idCategoria = getCategoriaParameter(tipo);
        }

        List<Producto> productos = productoService.limitByTypeField(idCategoria, "cat", 0, 100);

        req.setAttribute("productos", productos);

        req.setAttribute("tipo", tipo);

        req.setAttribute("title", "Express Controller" + " - " + "tabla " + tipo);

        req.setAttribute("cambiarDireccion", "true");

        this.getServletContext().getRequestDispatcher("/tableproducto.jsp").forward(req, resp);
    }

    private Integer getCategoriaParameter(String tipo) {
        Integer categoria;
        switch (tipo){
            case "videojuegos":
                categoria = 1;
                break;
            case "consolas":
                categoria = 2;
                break;
            case "mandos":
                categoria = 3;
                break;
            default:
                categoria = 0;
                break;
        }
        return categoria;
    }
}
