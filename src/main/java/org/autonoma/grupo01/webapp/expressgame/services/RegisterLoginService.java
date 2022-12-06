package org.autonoma.grupo01.webapp.expressgame.services;

import jakarta.servlet.http.HttpServletRequest;
import org.autonoma.grupo01.webapp.expressgame.models.Cliente;
import org.autonoma.grupo01.webapp.expressgame.models.Usuario;

import java.util.Map;
import java.util.Optional;

public interface RegisterLoginService {

    String secretKey = "ehprexx_gavex";

    Optional<Cliente> getCliente(HttpServletRequest req);

    Optional<Usuario> getUser(HttpServletRequest req);

    Map<String, String> getDataPaso(Boolean isRegister);

    String encrypt(String password);

    String decrypt(String passwordEncrypt);
}
