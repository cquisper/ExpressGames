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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet({"/formulario/cliente", "/formulario/administrador"})
public class FormClienteServelet extends HttpServlet {

    @Inject
    private ClienteService serviceCliente;

    @Inject
    @Security
    private RegisterLoginService registerLoginService;

    //! Util para mostrar lo que se valla a relenar de manera automatica
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id;
        String tipo = req.getServletPath().substring(12);
        if(tipo.contains("?")){
            tipo = tipo.substring(0, tipo.indexOf("?"));
        }
        try{
            id = Integer.valueOf(req.getParameter("idCliente"));
        }catch (NumberFormatException e){
            id=0;
        }
        //llenado vacio
        Cliente cliente = new Cliente();

        if(id>0){
            Optional<Cliente> optionalCliente = serviceCliente.porId(id);
            if(optionalCliente.isPresent()){
                cliente = optionalCliente.get();
                cliente.getUsuario().setPassword(registerLoginService.decrypt(cliente.getUsuario().getPassword())); // desencriptando
                req.setAttribute("title", "Express Controller" + " - Editar " + tipo);
            }
        }else{
            req.setAttribute("title", "Express Controller" + " - Insertar " + tipo);
        }

        req.setAttribute("cliente", cliente);

        req.setAttribute("tipo", tipo);

        this.getServletContext().getRequestDispatcher("/formcliente.jsp").forward(req, resp);
    }

    //!Enviar los datos por el formulario
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tipo = req.getServletPath().substring(12);

        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String dni = req.getParameter("dni");
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Integer idCliente;
        try{
            idCliente = Integer.valueOf(req.getParameter("id-cliente"));
            req.setAttribute("title", "Express Controller" + " - Editar " + tipo);
        }catch(NumberFormatException e){
            idCliente = 0;
            req.setAttribute("title", "Express Controller" + " - Insertar " + tipo);
        }

        Integer idUsuario;
        try{
            //!falta pero necesario
            idUsuario = Integer.valueOf(req.getParameter("id-usuario"));
        }catch(NumberFormatException e){
            idUsuario = 0;
        }

        Integer idRol;
        try{
            //!falta pero necesario
            idRol = Integer.valueOf(req.getParameter("rol"));
        }catch(NumberFormatException e){
            idRol = 0;
        }


        //!generando errores de vacio
        Map<String, String> errores = new HashMap<>();
        if (nombre == null || nombre.isBlank()){
            errores.put("nombre", "el nombre es requerido!");
        }
        if (apellido == null || apellido.isBlank()){
            errores.put("apellido", "el apellido es requerido!");
        }
        if (dni == null || dni.isBlank()){
            errores.put("dni", "el dni es requerido!");
        }
        if (email == null || email.isBlank()){
            errores.put("email", "el email es requerido!");
        }
        if (username == null || username.isBlank()){
            errores.put("username", "el username es requerido!");
        }
        if (password == null || password.isBlank()){
            errores.put("password", "el password es requerido!");
        }
        //!generando un nuevo cli
        // if()ente
        Cliente cliente = new Cliente();
        cliente.setId(idCliente);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setDni(dni);
        //usuario
        Usuario usuario = new Usuario();
        usuario.setId(idUsuario);
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setEmail(email);

        //rol del usuario
        Rol rol = new Rol();
        rol.setId(idRol);
        usuario.setRol(rol);
        cliente.setUsuario(usuario);

        //es al unico vasicamente que nos importa y el rol talves
        //!si todo sale bien -> cosa que dudo xd
        if(errores.isEmpty()){
            usuario.setPassword(registerLoginService.encrypt(usuario.getPassword()));//encriptando la contraseña
            serviceCliente.guardarCliente(cliente, usuario);
            resp.sendRedirect(req.getContextPath()+"/tabla/"+ tipo + "s");
        }else{
            //! tengo que insertar el id al cliente, y si recien se esta creando logicamente no hay nada por eso el error
            req.setAttribute("errores", errores);

            req.setAttribute("cliente", cliente);

            getServletContext().getRequestDispatcher("/formcliente.jsp").forward(req, resp);
        }

    }
}

