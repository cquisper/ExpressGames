package org.autonoma.grupo01.webapp.expressgame.models;

public class Distribuidor extends Persona{
    private String telefono;

    public Distribuidor() {
    }

    public Distribuidor(String nombre, String apellido, String dni, String telefono) {
        super(nombre, apellido, dni);
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
