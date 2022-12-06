package org.autonoma.grupo01.webapp.expressgame.services;

import jakarta.inject.Inject;
import org.autonoma.grupo01.webapp.expressgame.annotations.Service;
import org.autonoma.grupo01.webapp.expressgame.models.Cliente;
import org.autonoma.grupo01.webapp.expressgame.models.Usuario;
import org.autonoma.grupo01.webapp.expressgame.repositories.ClienteRepository;
import org.autonoma.grupo01.webapp.expressgame.repositories.UsuarioRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Inject
    private ClienteRepository clienteRepository;

    @Inject
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Cliente> listar() {
        try {
            return clienteRepository.listar();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Cliente> listarPorRol(Integer idRol) {
        try {
            return clienteRepository.listarPorRol(idRol);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Cliente> porIdUsuario(Integer id) {
        try {
            return Optional.ofNullable(clienteRepository.porIdUsuario(id));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Cliente> porId(Integer id) {
        try {
            return Optional.ofNullable(clienteRepository.porId(id));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Usuario> login(String usernameOrEmail, String password) {
        try {
            return Optional.ofNullable(usuarioRepository.porUsernameOrEmail(usernameOrEmail)).filter(u -> u.getPassword().equals(password));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardarCliente(Cliente cliente, Usuario usuario) {
        try{
            Usuario usuarioInsert = usuarioRepository.guardar(usuario);

            cliente.setUsuario(usuarioInsert);

            clienteRepository.guardar(cliente);
        }catch (SQLException e){
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void eliminarCliente(Integer idCliente, Integer idUsuario) {
        try {
            clienteRepository.eliminar(idCliente);
            usuarioRepository.eliminar(idUsuario);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
