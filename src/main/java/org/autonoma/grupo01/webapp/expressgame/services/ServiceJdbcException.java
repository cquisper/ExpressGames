package org.autonoma.grupo01.webapp.expressgame.services;

public class ServiceJdbcException extends RuntimeException{
    public ServiceJdbcException(String message){
        super(message);
    }

    public ServiceJdbcException(String message, Throwable cause) {
        super(message, cause);
    }
}
