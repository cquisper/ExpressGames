package org.autonoma.grupo01.webapp.expressgame.models;

public class Cliente extends Persona{
    private Usuario usuario;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String dni) {
        super(nombre, apellido, dni);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
