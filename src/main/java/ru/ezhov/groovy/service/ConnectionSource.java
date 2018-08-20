package ru.ezhov.groovy.service;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public class ConnectionSource {
    private static DataSource dataSource;

    public static Connection getConnection() throws Exception {
        if (dataSource == null) {
            InitialContext cxt = new InitialContext();
            if (cxt == null) {
                throw new Exception("Uh oh -- no context!");
            }
            DataSource ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/postgres");
            if (ds == null) {
                throw new Exception("Data source not found!");
            }
        }
        return dataSource.getConnection();
    }
}
