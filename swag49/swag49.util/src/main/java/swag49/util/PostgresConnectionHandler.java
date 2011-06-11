package swag49.util;

import org.apache.log4j.jdbcplus.JDBCConnectionHandler;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class PostgresConnectionHandler implements JDBCConnectionHandler {
    Connection con = null;
    String url = "jdbc:postgresql://localhost/swa";
    String username = "swa";
    String password = "swa11";

    static {
        try {
            // load driver
            Driver postgresDriver = (Driver) (Class.forName("org.postgresql.Driver").newInstance());
            DriverManager.registerDriver(postgresDriver);
        } catch (Exception e) {
            System.err.println("Could not register driver.");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return getConnection(url, username, password);
    }

    public Connection getConnection(String _url, String _username, String _password) {
        try {
            if (con != null && !con.isClosed())
                con.close();
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }

}
