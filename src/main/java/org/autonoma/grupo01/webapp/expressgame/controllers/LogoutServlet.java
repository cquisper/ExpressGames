package org.autonoma.grupo01.webapp.expressgame.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.autonoma.grupo01.webapp.expressgame.annotations.Security;
import org.autonoma.grupo01.webapp.expressgame.models.Usuario;
import org.autonoma.grupo01.webapp.expressgame.services.RegisterLoginService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Inject
    @Security
    private RegisterLoginService registerLoginService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Usuario> usuarioOptional = registerLoginService.getUser(req);

        if(usuarioOptional.isPresent()){
            req.getSession().invalidate();
            //req.getSession().removeAttribute("usuario");
        }

        resp.sendRedirect(req.getContextPath() + "/express.game");
    }
}
