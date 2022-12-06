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
import java.util.Optional;

@WebServlet("/producto")
public class ViewProductServlet extends HttpServlet {
    @Inject
    private ProductoService productoService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String codigo = req.getParameter("code");
        Optional<Producto> producto = productoService.selectByCode(codigo);
        producto.ifPresent(product -> req.setAttribute("producto", product));

        this.getServletContext().getRequestDispatcher("/producto.jsp").forward(req, resp);
    }
}
