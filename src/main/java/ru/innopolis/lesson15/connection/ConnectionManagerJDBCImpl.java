package ru.innopolis.lesson15.connection;

import ru.innopolis.lesson15.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerJDBCImpl implements ConnectionManager {

    private static final ConnectionManagerJDBCImpl instance = new ConnectionManagerJDBCImpl();

    private ConnectionManagerJDBCImpl() {
    }

    public static ConnectionManagerJDBCImpl getInstance() {
        return instance;
    }

    public Connection getConnection() throws SQLException {
        Configuration prop = Configuration.getInstance();
        return DriverManager.getConnection(prop.getProperty("urlDB"),
                prop.getProperty("userDB"), prop.getProperty("passwordDB"));
    }
}
