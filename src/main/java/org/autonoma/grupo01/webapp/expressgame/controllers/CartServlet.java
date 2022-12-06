package org.autonoma.grupo01.webapp.expressgame.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.autonoma.grupo01.webapp.expressgame.models.DetalleVenta;
import org.autonoma.grupo01.webapp.expressgame.models.Producto;
import org.autonoma.grupo01.webapp.expressgame.models.Venta;
import org.autonoma.grupo01.webapp.expressgame.services.ProductoService;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Optional;

@WebServlet("/carrito")
public class CartServlet extends HttpServlet {
    @Inject
    private ProductoService productoService;

    @Inject
    private Venta venta;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String codigo = req.getParameter("code");
        boolean isRemove = req.getParameter("retirar") != null && req.getParameter("retirar") != ""
                ? Boolean.valueOf(req.getParameter("retirar")) : false; //solo diosito sabe lo q hace xd

        if(isRemove){
            venta.retirarProducto(codigo);
        }else {
            Optional<Producto> producto = productoService.selectByCode(codigo);
            if(producto.isPresent()){
                DetalleVenta detVenta = new DetalleVenta(1, producto.get());
                venta.addDetalleVenta(detVenta);
            }
        }

        resp.sendRedirect(req.getContextPath() + "/carro.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> cantNames = req.getParameterNames();
        while (cantNames.hasMoreElements()){
            String cantParam = cantNames.nextElement();
            if(cantParam.startsWith("cant_")){
                String codigo = cantParam.substring(5);
                Integer cantidad = Integer.valueOf(req.getParameter(cantParam));
                if(cantidad > 0) {
                    venta.actualizarProducto(codigo, cantidad);
                }
            }else if (cantParam.equals("cantidad")){
                String codigo = req.getParameter("code");
                Optional<Producto> producto = productoService.selectByCode(codigo);
                Integer cantidad = Integer.valueOf(req.getParameter(cantParam));
                if(producto.isPresent() && cantidad > 0){
                    DetalleVenta detVenta = new DetalleVenta(cantidad, producto.get());
                    venta.addDetalleVenta(detVenta);
                }
            }
        }
        resp.sendRedirect(req.getContextPath() + "/carro.jsp");
    }
}
