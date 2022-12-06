package org.autonoma.grupo01.webapp.expressgame.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.ServletOutputStream;
import org.autonoma.grupo01.webapp.expressgame.annotations.Service;
import org.autonoma.grupo01.webapp.expressgame.models.DetalleVenta;
import org.autonoma.grupo01.webapp.expressgame.models.Venta;

import java.io.IOException;
import java.text.DecimalFormat;

@Service
public class ReporteServiceImpl implements ReporteService{

    private String rutaLogo = "C:\\Users\\prueb\\Documents\\JAVA_AUTONOMA\\webapp.express-game\\src\\main\\webapp\\assets\\image\\favicons\\android-chrome-512x512.png";

    private Venta boleta;

    private DecimalFormat formato = new DecimalFormat("#,###.00");

    @Override
    public void generarReporte(Venta boleta, ServletOutputStream streamReport) throws IOException {

        Document documentPDF = new Document();

        this.boleta = boleta;

        try {
            PdfWriter.getInstance(documentPDF, streamReport);
            documentPDF.open();
            Paragraph titulo = new Paragraph();
            Paragraph nombreProyecto = new Paragraph();
            Paragraph RUC = new Paragraph();

            titulo.setAlignment(Element.ALIGN_RIGHT);
            nombreProyecto.setAlignment(Element.ALIGN_RIGHT);
            RUC.setAlignment(Element.ALIGN_RIGHT);
            titulo.setFont(new Font(Font.FontFamily.COURIER, 18, Font.BOLD, BaseColor.BLACK));
            titulo.add("BOLETA DE VENTA ELECTRONICA");
            nombreProyecto.setFont(new Font(Font.FontFamily.HELVETICA, 14, Font.NORMAL, BaseColor.BLACK));
            nombreProyecto.add("Express Game");
            RUC.setFont(new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK));
            RUC.add("R.U.C. N° 20500128059");

            Image logo = Image.getInstance(rutaLogo);
            logo.scaleAbsolute(120, 120);
            logo.setAbsolutePosition(35,700);

            Paragraph cliente = getInfoCliente();

            Paragraph infoBoleta = getInfoBoleta();

            Paragraph distribuidor = getInfoDist();

            PdfPTable tablaProducto = getTablaProductoInformacion();

            Paragraph nota = getNota();

            PdfPTable line1 = new PdfPTable(4);
            line1.setWidthPercentage(100);
            PdfPCell cline1 = new PdfPCell();
            cline1.setBackgroundColor(BaseColor.BLACK);
            cline1.setColspan(4);
            line1.addCell(cline1);

            //Añadiendo los elementos
            documentPDF.add(logo);
            documentPDF.add(titulo);
            documentPDF.add(nombreProyecto);
            documentPDF.add(RUC);
            documentPDF.add(Chunk.NEWLINE);
            documentPDF.add(Chunk.NEWLINE);
            documentPDF.add(Chunk.NEWLINE);
            documentPDF.add(Chunk.NEWLINE);
            documentPDF.add(line1);
            documentPDF.add(Chunk.NEWLINE);
            documentPDF.add(cliente);
            documentPDF.add(Chunk.NEWLINE);
            documentPDF.add(infoBoleta);
            documentPDF.add(Chunk.NEWLINE);
            documentPDF.add(line1);
            documentPDF.add(Chunk.NEWLINE);
            documentPDF.add(distribuidor);
            documentPDF.add(Chunk.NEWLINE);
            documentPDF.add(line1);
            documentPDF.add(Chunk.NEWLINE);
            documentPDF.add(tablaProducto);
            documentPDF.add(Chunk.NEWLINE);
            documentPDF.add(nota);
            documentPDF.close();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    private Paragraph getNota() {
        Paragraph nota = new Paragraph();
        nota.setFont(new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD));

        nota.add("Nota: ");
        nota.setFont(new Font(Font.FontFamily.HELVETICA, 11));
        nota.add("Realmente apreciamos su confianza en nuestra empresa, si hay algo más que podamos hacer, haganoslo saber!");
        nota.setAlignment(Element.ALIGN_JUSTIFIED);

        return nota;
    }

    private PdfPTable getTablaProductoInformacion() {
        PdfPTable tablaProducto = new PdfPTable(4);
        tablaProducto.setWidthPercentage(100);

        PdfPCell nombre = new PdfPCell();
        Phrase phNombre = new Phrase();
        phNombre.setFont(new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD,BaseColor.WHITE));
        phNombre.add("Productos");
        nombre.setBackgroundColor(BaseColor.BLACK);
        nombre.addElement(phNombre);

        PdfPCell cantidad = new PdfPCell();
        Phrase phCantidad= new Phrase();
        phCantidad.setFont(new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD,BaseColor.WHITE));
        phCantidad.add("Cantidad");
        cantidad.setBackgroundColor(BaseColor.BLACK);
        cantidad.addElement(phCantidad);

        PdfPCell precio = new PdfPCell();
        Phrase phPrecio= new Phrase();
        phPrecio.setFont(new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD,BaseColor.WHITE));
        phPrecio.add("Precio");
        precio.setBackgroundColor(BaseColor.BLACK);
        precio.addElement(phPrecio);

        PdfPCell total = new PdfPCell();
        Phrase phTotal= new Phrase();
        phTotal.setFont(new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD,BaseColor.WHITE));
        phTotal.add("Total");
        total.setBackgroundColor(BaseColor.BLACK);
        total.addElement(phTotal);

        tablaProducto.addCell(nombre);
        tablaProducto.addCell(cantidad);
        tablaProducto.addCell(precio);
        tablaProducto.addCell(total);

        for (DetalleVenta detVenta : boleta.getDetalleVentas()) {
            tablaProducto.addCell(detVenta.getProducto().getNombre());
            tablaProducto.addCell(detVenta.getCantidad().toString());
            tablaProducto.addCell("S/ "+formato.format(detVenta.getPrecio()));
            tablaProducto.addCell("S/ "+formato.format(detVenta.getImporteBol()));
        }

        PdfPCell monto = new PdfPCell(new Phrase("Monto total a pagar: S/ "+formato.format(boleta.getMontoBol())));
        monto.setHorizontalAlignment(Element.ALIGN_RIGHT);
        monto.setBackgroundColor(new BaseColor(238, 238, 238));
        monto.setColspan(4);

        tablaProducto.addCell(monto);

        return tablaProducto;
    }

    private Paragraph getInfoDist() {
        Paragraph distribuidor = new Paragraph();

        distribuidor.setFont(new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD));
        distribuidor.add("Distribuido por \n"+boleta.getDistribuidor().getNombre()+" "+boleta.getDistribuidor().getApellido()+"\n");
        distribuidor.setFont(new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD));
        distribuidor.add("DNI: ");
        distribuidor.setFont(new Font(Font.FontFamily.HELVETICA, 11));
        distribuidor.add(" "+boleta.getDistribuidor().getDni()+"\n");
        distribuidor.setFont(new Font(Font.FontFamily.HELVETICA,11, Font.BOLD));
        distribuidor.add("Telefono:");
        distribuidor.setFont(new Font(Font.FontFamily.HELVETICA, 11));
        distribuidor.add(" +51 "+boleta.getDistribuidor().getTelefono()+"\n");

        return distribuidor;
    }

    private Paragraph getInfoBoleta() {
        Paragraph infoBoleta = new Paragraph();

        infoBoleta.setFont(new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD));
        infoBoleta.add("Boleta N°: ");
        infoBoleta.setFont(new Font(Font.FontFamily.HELVETICA, 11));
        infoBoleta.add(" "+boleta.getId()+" \n");
        infoBoleta.setFont(new Font(Font.FontFamily.HELVETICA,11, Font.BOLD));
        infoBoleta.add("Fecha de pago:");
        infoBoleta.setFont(new Font(Font.FontFamily.HELVETICA, 11));
        infoBoleta.add(" "+boleta.getFecha().toString()+" \n");
        infoBoleta.setFont(new Font(Font.FontFamily.HELVETICA,11, Font.BOLD));
        infoBoleta.add("Monto total: ");
        infoBoleta.setFont(new Font(Font.FontFamily.HELVETICA, 11));
        infoBoleta.add(" S/ "+formato.format(boleta.getMontoBol()));

        return infoBoleta;
    }

    private Paragraph getInfoCliente() {
        Paragraph cliente = new Paragraph();
        String[] direccion = boleta.getDireccionEntrega().split(", ");

        cliente.setFont(new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD));
        cliente.add("Boleta a \n"+boleta.getCliente().getNombre()+" "+boleta.getCliente().getApellido()+"\n");
        cliente.setFont(new Font(Font.FontFamily.HELVETICA, 11));
        cliente.add(direccion[0]+"\n");
        cliente.add(direccion[2]+", "+direccion[1]+"\nPerú\n");
        cliente.add("DNI: "+boleta.getCliente().getDni()+"\nEmail: "+boleta.getCliente().getUsuario().getEmail());

        return cliente;
    }
}
