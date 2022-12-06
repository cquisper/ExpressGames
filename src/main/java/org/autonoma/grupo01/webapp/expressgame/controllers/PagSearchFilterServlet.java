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
import java.util.Enumeration;
import java.util.List;

@WebServlet({"/gaming"})
public class PagSearchFilterServlet extends HttpServlet {

    @Inject
    private ProductoService productoService;

    private float cantidadProd = 9f;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Enumeration<String> filtroNames = req.getParameterNames();

        String filter = "";

        String title = "";

        boolean isSearch = false, isMinMax = false;

        double nPagination;

        List<Producto> productos;

        //Aqui empieza la paginacion :D
        Integer p = req.getParameter("p") != null ? Integer.parseInt(req.getParameter("p")) : 1;

        Integer inicio = ((p-1) * (int)cantidadProd); //Inicio del limit

        //Filtro
        while (filtroNames.hasMoreElements()){
            String param = filtroNames.nextElement();
            if(param.equals("search")){
                isSearch = true;
                break;
            }else if(param.equals("min") || param.equals("max")){
                isMinMax = true;
                break;
            }
        }

        if(isSearch){
            String categoria = req.getParameter("categoria");
            String busqueda = req.getParameter("search");
            req.setAttribute("categoria", categoria);
            filter = "categoria="+categoria+"&search="+busqueda;
            title = busqueda;

            nPagination = (productoService.countBySearch(Integer.parseInt(categoria), busqueda) / cantidadProd); // numeros de paginacion = (cantidad de productos / numero de productos a mostrar en cd pagina)
            productos = productoService.limitBySearch(Integer.parseInt(categoria), busqueda, inicio, (int)cantidadProd);
        }else if(isMinMax) {
            String minimo = req.getParameter("min");
            String maximo = req.getParameter("max");
            filter = "min="+minimo+"&max="+maximo;
            title = "precio " + minimo + " a " + maximo;

            nPagination = (productoService.countByPrice(Double.parseDouble(minimo), Double.parseDouble(maximo)) / cantidadProd);
            productos = productoService.limitByPrice(Double.parseDouble(minimo), Double.parseDouble(maximo), inicio, (int)cantidadProd);
        }else{
            String tipo = (req.getParameter("cat")) != null ? "cat" : (req.getParameter("plat") != null) ? "plat" : (req.getParameter("scat") != null ? "scat" : "");
            Integer idTipo = tipo.equals("") || tipo.equals("0") ? 1 : Integer.parseInt(req.getParameter(tipo));
            filter = tipo + "=" + idTipo;
            title = (req.getParameter("cat")) != null ? "Categoria" : (req.getParameter("plat") != null) ? "Plataforma" : (req.getParameter("scat") != null ? "Genero" : "");

            nPagination = (productoService.countByTypeField(idTipo, tipo) / cantidadProd);
            productos = productoService.limitByTypeField(idTipo, tipo, inicio, (int)cantidadProd);
        }
        //Redireccionamiento
        double pagination = Math.ceil(nPagination); //Redondear para arriba ej 2.6 -> 3

        req.setAttribute("productos", productos);

        req.setAttribute("pagination", (int)pagination);

        req.setAttribute("filter", filter);

        req.setAttribute("title", title + " - " +req.getAttribute("title"));

        this.getServletContext().getRequestDispatcher( "/gaming.jsp?p=" + p).forward(req, resp);
    }
}
