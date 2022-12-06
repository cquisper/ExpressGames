package org.autonoma.grupo01.webapp.expressgame.transactions;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import org.autonoma.grupo01.webapp.expressgame.annotations.MysqlConn;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@ApplicationScoped
public class ConnectionResources {

    @Resource(name = "jdbc/mysqlDB")
    private DataSource ds;

    @Produces //Se utiliza esta anotacion cuando queremos usar clases externas de java en el contexto CDI
    @RequestScoped
    @MysqlConn //Calificador
    private Connection beanConnection() throws SQLException{
        return ds.getConnection();
    }

    /*
    El dispose indica que una ve< que finaliza el contexto (en este caso el @RequestScode (linea 22)) se cierre la conexion pero indicandole
    con el qualifiry cual debe cerrar con el @MysqlConn, esto tbm aplicaria para otras coneecciones a diferentes motores de bd o clases que tengan
    metodos close()
     */
    public void close(@Disposes @MysqlConn Connection conn) throws SQLException {
        conn.close();
    }
}
