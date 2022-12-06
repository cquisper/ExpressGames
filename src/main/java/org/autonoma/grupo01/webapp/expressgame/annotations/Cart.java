package org.autonoma.grupo01.webapp.expressgame.annotations;

import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Named;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@SessionScoped
@Named
@Stereotype //Es para juntar diferentes anotaciones como el named, el contexto cdi, los transaccionales, etc
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Cart {
}
