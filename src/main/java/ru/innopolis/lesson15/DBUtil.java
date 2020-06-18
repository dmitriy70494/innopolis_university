package ru.innopolis.lesson15;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
    private DBUtil() {
        throw new AssertionError();
    }

    public static void renewDatabase() throws SQLException {
        //TODO: написать создание своей БД
        try (Connection connection = DBUtil.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(SQLConstant.DROP_TABLE_BRANDS +
                    SQLConstant.DROP_TABLE_BRANDS_HISTORY +
                    SQLConstant.CREATE_TABLE_BRANDS +
                    SQLConstant.CREATE_TABLE_BRANDS_HISTORY);
        }
    }

    public static Connection getConnection() throws SQLException {
        PropertiesApl prop = PropertiesApl.getInstance();
        return DriverManager.getConnection(prop.getProperty("urlDB"),
                prop.getProperty("userDB"), prop.getProperty("passwordDB"));
    }
}
