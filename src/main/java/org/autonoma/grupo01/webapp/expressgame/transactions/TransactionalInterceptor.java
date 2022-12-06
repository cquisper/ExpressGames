package org.autonoma.grupo01.webapp.expressgame.transactions;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.autonoma.grupo01.webapp.expressgame.annotations.MysqlConn;
import org.autonoma.grupo01.webapp.expressgame.annotations.TransactionalJdbc;
import org.autonoma.grupo01.webapp.expressgame.services.ServiceJdbcException;

import java.sql.Connection;

@TransactionalJdbc
@Interceptor
public class TransactionalInterceptor {

    @Inject
    @MysqlConn
    private Connection conn;

    @AroundInvoke //El metodo que va a interceptar se le anota con el arroundInvoke
    public Object transactional(InvocationContext invocation) throws Exception {
        if(conn.getAutoCommit()){
            conn.setAutoCommit(false);
        }

        try{
            /*
            Se invoca el metodo, puede lanzar una exception de tipo ServiceJdbcException, que es nuestra exception personalizaada
             */
            Object result = invocation.proceed();
            conn.commit(); //Hace el commit

            return result;
        } catch (ServiceJdbcException e) {
            conn.rollback(); // se hace el rollbrack
            //Se relanza la misma exception, puesto que ya fue lanzada (ProductoServiceJdbc) asi que no es necesario lanzar una nueva
            throw e;
        }
    }
}
