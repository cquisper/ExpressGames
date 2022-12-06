package org.autonoma.grupo01.webapp.expressgame.services;

import jakarta.servlet.ServletOutputStream;
import org.autonoma.grupo01.webapp.expressgame.models.Venta;

import java.io.IOException;

public interface ReporteService {

    void generarReporte(Venta boleta, ServletOutputStream streamReport) throws IOException;

}
