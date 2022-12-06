package org.autonoma.grupo01.webapp.expressgame.models;

import jakarta.annotation.PostConstruct;
import org.autonoma.grupo01.webapp.expressgame.annotations.Cart;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Cart
public class Venta implements Serializable {
    private Integer id;
    private Cliente cliente;
    private Distribuidor distribuidor;
    private Double monto;
    private String direccionEntrega;
    private LocalDate fecha;

    private DetalleVenta detalleVenta;
    private List<DetalleVenta> detalleVentas;

    @PostConstruct
    public void inicializar(){
        this.detalleVentas = new ArrayList<>();
    }

    public Venta() {
        this.detalleVentas = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Distribuidor getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(Distribuidor distribuidor) {
        this.distribuidor = distribuidor;
    }

    public Double getMonto() {
        this.monto = detalleVentas.stream().mapToDouble(detVenta -> detVenta.getImporte()).sum();
        return monto;
    }

    public Double getMontoBol(){
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public DetalleVenta getDetalleVenta() {
        return detalleVenta;
    }

    public void setDetalleVenta(DetalleVenta detalleVenta) {
        this.detalleVenta = detalleVenta;
    }

    public List<DetalleVenta> getDetalleVentas() {
        return detalleVentas;
    }

    public void setDetalleVentas(List<DetalleVenta> detalleVentas) {
        this.detalleVentas = detalleVentas;
    }

    public void addDetalleVenta(DetalleVenta detVenta){
        if(detalleVentas.contains(detVenta)){
            Optional<DetalleVenta> optionalDetVenta = detalleVentas.stream().filter(dtVenta -> dtVenta.equals(detVenta)).findAny();
            //Producto repetido entonces...se aumenta su cantidad
            optionalDetVenta.ifPresent(detalleVenta -> detalleVenta.setCantidad(detalleVenta.getCantidad() + 1));
        }else{
            this.detalleVentas.add(detVenta);
        }
    }

    public void retirarProducto(String codigoProducto){
        Optional<DetalleVenta> optionalDetVenta = this.detalleVentas.stream()
                .filter(detVenta -> detVenta.getProducto().getCodigo().equals(codigoProducto)).findAny();
        optionalDetVenta.ifPresent(detalleVenta -> this.detalleVentas.remove(detalleVenta));
    }

    public void actualizarProducto(String codigoProducto, Integer cantidad){
        Optional<DetalleVenta> optionalDetVenta = this.detalleVentas.stream()
                .filter(detVenta -> detVenta.getProducto().getCodigo().equals(codigoProducto)).findAny();
        optionalDetVenta.ifPresent(detalleVenta -> detalleVenta.setCantidad(cantidad));
    }

    public Integer getCantidadProductos(){
        return this.detalleVentas.stream().mapToInt(detVenta -> detVenta.getCantidad()).sum();
    }
}
