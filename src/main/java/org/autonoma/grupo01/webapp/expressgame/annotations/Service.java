package org.autonoma.grupo01.webapp.expressgame.annotations;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Named;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@TransactionalJdbc
@ApplicationScoped
@Named
@Stereotype //Junta varias anotaciones en una, es para dar una semantica. Junta varrias clases o beans dandole un identificador
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Service {
}
