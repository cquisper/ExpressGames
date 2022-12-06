package org.autonoma.grupo01.webapp.expressgame.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.autonoma.grupo01.webapp.expressgame.models.Plataforma;
import org.autonoma.grupo01.webapp.expressgame.models.Producto;
import org.autonoma.grupo01.webapp.expressgame.models.SubCategoria;
import org.autonoma.grupo01.webapp.expressgame.services.ProductoService;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet({"/formulario/videojuego","/formulario/consola","/formulario/mando"})
public class FormProductServlet extends HttpServlet {
    @Inject
    private ProductoService productoService;

    private Map<String, String> errores;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tipo = req.getServletPath().substring(12);
        if(tipo.contains("?")){
            tipo = tipo.substring(0, tipo.indexOf("?"));
        }
        String codEdit = req.getParameter("codEdit");
        Integer categoria;

        Producto p = new Producto();
        p.setPlataforma(new Plataforma());
        p.setSubCategoria(new SubCategoria());

        categoria = getCategoriaParameter(req, tipo);

        if(codEdit != null){
            Optional<Producto> optionalProducto = productoService.selectByCode(codEdit);
            if(optionalProducto.isPresent()){
                p = optionalProducto.get();
                if(categoria == 2 || categoria == 3){
                    Object[] espfSaltoObject = Arrays.stream(p.getEspecificacion()).map(s -> s + "\n").toArray();
                    String[] espfSalto = new String[espfSaltoObject.length];
                    for (int i = 0; i < espfSaltoObject.length; i++) {
                        espfSalto[i] = espfSaltoObject[i].toString();
                    }
                    espfSalto[espfSalto.length-1] = espfSalto[espfSalto.length-1].replace("\n", "");
                    p.setEspecificacion(espfSalto);
                }
                req.setAttribute("title", "Express Controller" + " - Editar " + tipo);
            }
        }else{
            req.setAttribute("title", "Express Controller" + " - Insertar " + tipo);
        }
        req.setAttribute("subCategorias", productoService.listarSubCategoriaporCategoria(categoria));

        req.setAttribute("plataformas", productoService.listarPlataforma());

        req.setAttribute("cambiarDireccion", "true");

        req.setAttribute("producto", p);

        this.getServletContext().getRequestDispatcher("/formproducto.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tipo = req.getServletPath().substring(12);
        errores = new HashMap<>();
        Producto p = getProductoParameter(req, tipo);
        if(errores.isEmpty()){
            productoService.guardarProducto(p);

            resp.sendRedirect(req.getContextPath() + "/tabla/" + tipo + "s");
        }else{
            Integer categoria = getCategoriaParameter(req, tipo);

            req.setAttribute("errores", errores);

            req.setAttribute("subCategorias", productoService.listarSubCategoriaporCategoria(categoria));

            req.setAttribute("plataformas", productoService.listarPlataforma());

            req.setAttribute("producto", p);

            this.getServletContext().getRequestDispatcher("/formproducto.jsp").forward(req, resp);
        }
    }

    private Integer getCategoriaParameter(HttpServletRequest req, String tipo) {
        Integer categoria;
        switch (tipo){
            case "videojuego":
                categoria = 1;
                req.setAttribute("tipo","videojuego");
                break;
            case "consola":
                categoria = 2;
                req.setAttribute("tipo","consola");
                break;
            case "mando":
                categoria = 3;
                req.setAttribute("tipo","mando");
                break;
            default:
                categoria = 0;
                req.setAttribute("tipo","sin resultados");
                break;
        }
        return categoria;
    }

    private Producto getProductoParameter(HttpServletRequest req, String tipo) {
        String nombre = req.getParameter("nombre");
        Double precio;
        try {
            precio = Double.parseDouble(req.getParameter("precio"));
        }catch (NumberFormatException e){
            precio = 0.0;
        }
        String codigo = req.getParameter("codigo");
        String imagenRuta = req.getParameter("imagenRuta");

        Integer idPlataforma;
        try {
            idPlataforma = Integer.parseInt(req.getParameter("plataforma"));
        }catch (NumberFormatException e){
            idPlataforma = 0;
        }
        Integer idSubCategoria;
        try{
            idSubCategoria = Integer.parseInt(req.getParameter("subCategoria"));
        }catch (NumberFormatException e){
            idSubCategoria = 0;
        }

        String descripcion = req.getParameter("descripcion");
        String especificacion;
        if(tipo.equals("videojuego")){
            especificacion = req.getParameter("especificacion");
            String urlTrailer = req.getParameter("urlTrailer");
            especificacion = ((urlTrailer != null) && urlTrailer.contains("youtube")) ? urlTrailer.concat("&").concat(especificacion) : especificacion;
        }else{
            especificacion = req.getParameter("especificacion").replaceAll("\n","&");
        }

        contarErrores(nombre, precio, codigo, imagenRuta, idPlataforma, idSubCategoria, descripcion, especificacion);

        Integer idEdit;
        try{
            idEdit = Integer.parseInt(req.getParameter("idEdit"));
            req.setAttribute("title", "Express Controller" + " - Editar " + tipo);
        }catch (NumberFormatException e){
            idEdit = 0;
            req.setAttribute("title", "Express Controller" + " - Insertar " + tipo);
        }

        Producto p = new Producto();
        p.setId(idEdit);
        p.setNombre(nombre);
        p.setPrecio(precio);
        p.setCodigo(codigo);
        p.setImagenRuta(imagenRuta);
        p.setDescripcion(descripcion);
        p.setEspecificacionStr(especificacion);

        Plataforma pl = new Plataforma();
        pl.setId(idPlataforma);

        SubCategoria sc = new SubCategoria();
        sc.setId(idSubCategoria);

        p.setPlataforma(pl);
        p.setSubCategoria(sc);

        return p;
    }

    private void contarErrores(String nombre, Double precio, String codigo, String imagenRuta, Integer idPlataforma, Integer idSubCategoria, String descripcion, String especificacion) {
        if(nombre == null || nombre.isBlank()){
            errores.put("nombre", "El campo nombre esta vacio");
        }
        if(precio.equals(0.0)){
            errores.put("precio", "El campo precio esta vacio");
        }
        if(codigo == null || codigo.isBlank()){
            errores.put("codigo", "El campo codigo esta vacio");
        }
        if(imagenRuta == null || imagenRuta.isBlank()){
            errores.put("imagenRuta", "No se ingreso ninguna ruta URL de la imagen");
        }else if (!imagenRuta.contains("https://")){
            errores.put("imagenRuta", "La ruta URL ingresada no es valida");
        }
        if(idPlataforma.equals(0)){
            errores.put("plataforma", "No selecciono ninguna opción");
        }
        if(idSubCategoria.equals(0)){
            errores.put("subCategoria", "No selecciono ninguna opción");
        }
        if(descripcion == null || descripcion.isBlank()){
            errores.put("descripcion", "El campo descripción esta vacio");
        }
        if(especificacion == null || especificacion.isBlank()){
            errores.put("especificacion", "El campo especificación esta vacio");
        }
    }
}
