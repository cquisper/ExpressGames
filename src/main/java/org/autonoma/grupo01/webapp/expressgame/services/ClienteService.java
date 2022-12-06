package org.autonoma.grupo01.webapp.expressgame.services;

import org.autonoma.grupo01.webapp.expressgame.models.Cliente;
import org.autonoma.grupo01.webapp.expressgame.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    List<Cliente> listar();

    List<Cliente> listarPorRol(Integer idRol);

    Optional<Cliente> porIdUsuario(Integer id);

    Optional<Cliente> porId(Integer id);

    Optional<Usuario> login(String usernameOrEmail, String password);

    void guardarCliente(Cliente cliente, Usuario usuario);

    void eliminarCliente(Integer idCliente, Integer idUsuario);
}
