package org.autonoma.grupo01.webapp.expressgame.models;

import java.util.Objects;

public class DetalleVenta {
    private Integer id;
    private Integer idVenta;
    private Producto producto;
    private Integer cantidad;
    private Double precio;
    private Double importe;

    public DetalleVenta() {
    }

    public DetalleVenta(Integer cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getImporte() {
        this.importe = this.cantidad * this.producto.getPrecio();
        return importe;
    }

    public Double getImporteBol() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleVenta detVenta = (DetalleVenta) o;
        return Objects.equals(producto.getCodigo(), detVenta.producto.getCodigo())
                && Objects.equals(producto.getNombre(), detVenta.producto.getNombre());
    }
}
