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

@WebServlet({"/eliminar/videojuego","/eliminar/consola","/eliminar/mando"})
public class DeleteProductServlet extends HttpServlet {
    @Inject
    private ProductoService productoService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer idProducto;

        String tipo = req.getServletPath().substring(10);

        try{
            idProducto = Integer.parseInt(req.getParameter("idDelete"));
        }catch (NumberFormatException e){
            idProducto = 0;
        }
        if(idProducto > 0) {

            Optional<Producto> optionalProducto = productoService.porIdProducto(idProducto);

            if (optionalProducto.isPresent()) {

                productoService.eliminarProducto(idProducto);

                resp.sendRedirect(req.getContextPath() + "/tabla/" + tipo + "s");
            }else{
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el producto en la base de datos");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Error el id es null, se debe enviar como parametro en la url!");
        }
    }
}
