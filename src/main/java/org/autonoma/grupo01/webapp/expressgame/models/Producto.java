package org.autonoma.grupo01.webapp.expressgame.models;

public class Producto {
    private Integer id;
    private SubCategoria subCategoria;
    private Plataforma plataforma;
    private String nombre;
    private String descripcion;
    private String[] especificacion;
    private String especificacionStr;
    private Double precio;
    private String imagenRuta;
    private String codigo;
    private String estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SubCategoria getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(SubCategoria subCategoria) {
        this.subCategoria = subCategoria;
    }

    public Plataforma getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public Double getPrecio() {
        return precio;
    }

    public void setEspecificacion(String[] especificacion) {
        this.especificacion = especificacion;
    }

    public String[] getEspecificacion() {
        return especificacion;
    }

    public String getEspecificacionStr() {
        return especificacionStr;
    }

    public void setEspecificacionStr(String especificacionStr) {
        this.especificacionStr = especificacionStr;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getImagenRuta() {
        return imagenRuta;
    }

    public void setImagenRuta(String imagenRuta) {
        this.imagenRuta = imagenRuta;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
