package org.autonoma.grupo01.webapp.expressgame.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class AppListener implements ServletContextListener, ServletRequestListener, HttpSessionListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("titleApp", "EXPRESS GAME");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        sre.getServletRequest().setAttribute("title", "Express Game");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute("modalCompra", "false");
    }
}
