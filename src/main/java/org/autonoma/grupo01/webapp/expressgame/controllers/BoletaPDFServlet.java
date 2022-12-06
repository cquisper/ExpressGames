package org.autonoma.grupo01.webapp.expressgame.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.autonoma.grupo01.webapp.expressgame.annotations.Security;
import org.autonoma.grupo01.webapp.expressgame.models.DetalleVenta;
import org.autonoma.grupo01.webapp.expressgame.models.Usuario;
import org.autonoma.grupo01.webapp.expressgame.models.Venta;
import org.autonoma.grupo01.webapp.expressgame.services.RegisterLoginService;
import org.autonoma.grupo01.webapp.expressgame.services.ReporteService;
import org.autonoma.grupo01.webapp.expressgame.services.VentaService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet({"/view/boleta", "/boletaVentaElectronica"})
public class BoletaPDFServlet extends HttpServlet {
    @Inject
    private VentaService ventaService;
    @Inject
    @Security
    private RegisterLoginService registerLoginService;
    @Inject
    private ReporteService reporteService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer idVenta;

        try{
            idVenta = Integer.parseInt(req.getParameter("idVenta"));
        }catch (NumberFormatException e){
            idVenta = 0;
        }

        Optional<Venta> optionalBoleta = ventaService.porId(idVenta);

        if(optionalBoleta.isPresent()){
            List<DetalleVenta> detalleVentas = ventaService.listarDetVentaPorIdVenta(optionalBoleta.get().getId());
            Usuario usuario = registerLoginService.getUser(req).get();
            Venta boleta = optionalBoleta.get();
            boleta.setDetalleVentas(detalleVentas);
            boleta.getCliente().setUsuario(usuario);

            req.getSession().setAttribute("boleta", boleta);
            resp.sendRedirect(req.getContextPath() + "/boleta.jsp");
        }else{
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "EL RECURSO QUE SOLICITO NO SE ENCUENTRA DISPONIBLE");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer idVenta;

        try{
            idVenta = Integer.parseInt(req.getParameter("idVenta"));
        }catch (NumberFormatException e){
            idVenta = 0;
        }

        Optional<Venta> optionalBoleta = ventaService.porId(idVenta);

        if(optionalBoleta.isPresent()){
            List<DetalleVenta> detalleVentas = ventaService.listarDetVentaPorIdVenta(optionalBoleta.get().getId());
            Usuario usuario = registerLoginService.getUser(req).get();
            Venta boleta = optionalBoleta.get();
            boleta.setDetalleVentas(detalleVentas);
            boleta.getCliente().setUsuario(usuario);

            resp.setContentType("application/pdf");
            resp.setHeader("Content-Disposition", "inline;filename=boleta_venta_id_"+boleta.getId()+".pdf");
            resp.setHeader("Cache-Control", "no-cache");
            resp.setHeader("Pragma", "no-cache");
            resp.setDateHeader("Expires", 0);

            reporteService.generarReporte(boleta, resp.getOutputStream());
        }else{
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "EL RECURSO QUE SOLICITO NO SE ENCUENTRA DISPONIBLE");
        }
    }
}
