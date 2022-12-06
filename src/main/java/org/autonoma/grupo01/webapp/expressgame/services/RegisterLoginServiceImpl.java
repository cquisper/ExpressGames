package org.autonoma.grupo01.webapp.expressgame.services;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Base64;
import org.autonoma.grupo01.webapp.expressgame.annotations.Security;
import org.autonoma.grupo01.webapp.expressgame.annotations.Service;
import org.autonoma.grupo01.webapp.expressgame.models.Cliente;
import org.autonoma.grupo01.webapp.expressgame.models.Usuario;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Security
public class RegisterLoginServiceImpl implements RegisterLoginService{

    @Override
    public Optional<Cliente> getCliente(HttpServletRequest req) {
        Object cliente = req.getSession().getAttribute("cliente");

        return (cliente != null) ? (Optional<Cliente>)cliente : Optional.empty();
    }

    @Override
    public Optional<Usuario> getUser(HttpServletRequest req) {

        Object usuario = req.getSession().getAttribute("usuario");

        return (usuario != null) ? (Optional<Usuario>) usuario : Optional.empty();
    }

    @Override
    public Map<String, String> getDataPaso(Boolean isRegister) {
        Map<String, String> dataPaso = new HashMap<>();
        if(isRegister){
            dataPaso.put("register", "true");
            dataPaso.put("data-paso-1", "2");
            dataPaso.put("data-paso-2", "1");
        }else{
            dataPaso.put("register", "false");
            dataPaso.put("data-paso-1", "1");
            dataPaso.put("data-paso-2", "2");
        }
        return dataPaso;
    }

    @Override
    public String encrypt(String password) {
        String passwordEncrypt = "";

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] keyPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] bytesKey = Arrays.copyOf(keyPassword, 24);
            SecretKey sk = new SecretKeySpec(bytesKey, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, sk);

            byte[] plainTextByte = password.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextByte);

            byte[] base64Bytes = Base64.encodeBase64(buf);

            passwordEncrypt = new String(base64Bytes);

        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
        return passwordEncrypt;
    }

    @Override
    public String decrypt(String passwordEncrypt) {
        String passwordDesEncrypt = "";

        try {
            byte[] message = Base64.decodeBase64(passwordEncrypt.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword,24);

            SecretKey sk = new SecretKeySpec(keyBytes, "DESede");
            Cipher deCipher = Cipher.getInstance("DESede");

            deCipher.init(Cipher.DECRYPT_MODE, sk);
            byte[] plainTextByte = deCipher.doFinal(message);

            passwordDesEncrypt = new String(plainTextByte, "utf-8");

        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }

        return passwordDesEncrypt;
    }
}
