package org.autonoma.grupo01.webapp.expressgame.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.autonoma.grupo01.webapp.expressgame.models.Cliente;
import org.autonoma.grupo01.webapp.expressgame.services.ClienteService;

import java.io.IOException;
import java.util.Optional;

@WebServlet({"/eliminar/cliente", "/eliminar/administrador"})
public class DeleteClienteServlet extends HttpServlet {
    @Inject
    private ClienteService clienteService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer idCliente;

        String tipo = req.getServletPath().substring(10);

        try{
            idCliente=Integer.parseInt(req.getParameter("idCLiente"));
        }catch (NumberFormatException e){
            //? por si nos envian una letra lo chapamos como 0
            idCliente = 0;
        }

        if(idCliente > 0){
            Optional<Cliente> optionalCliente = clienteService.porId(idCliente);
            //!Existe el cliente
            if(optionalCliente.isPresent()){

                clienteService.eliminarCliente(idCliente, optionalCliente.get().getUsuario().getId());
                //! Ojo este en el reposiotorio se encarga de elliminar su usuario

                //! Problema necesitamos pasarle ->  serviceUsuario.eliminar(1); tranqui yo lo hago
                //!cuando se elimine redireccoinar al a pagina que muestra los clientes
                resp.sendRedirect(req.getContextPath()+"/tabla/"+ tipo + "s");//? el mismo tiene su vservelet para iterar
            }else{
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el cliente en la base de datos!");
            }

        } else{
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Error el id es null, se debe enviar como parametro en la url!");
        }

    }
}
