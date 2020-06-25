package ru.innopolis.lesson15;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.lesson15.connection.ConnectionManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Configuration {
    public static final String DROP_TABLE_BRANDS = "DROP TABLE IF EXISTS brands;";
    public static final String CREATE_TABLE_BRANDS =
            "CREATE TABLE brands (\n"
                    + "    id SERIAL primary key,\n"
                    + "    name varchar(100) NOT NULL,\n"
                    + "    price integer NOT NULL,\n"
                    + "    count integer NOT NULL);";
    public static final String DROP_TABLE_BRANDS_HISTORY = "DROP TABLE IF EXISTS brands_history;";
    public static final String CREATE_TABLE_BRANDS_HISTORY =
            "CREATE TABLE brands_history (\n"
                    + "    id SERIAL primary key,\n"
                    + "    name varchar(100) NOT NULL,\n"
                    + "    price integer NOT NULL,\n"
                    + "    count integer NOT NULL,"
                    + "    brand_id integer NOT NULL);";

    private static final String PATH_TO_PROPERTIES = "src/main/resources/lesson15.properties";

    private static volatile Configuration instance;

    private Properties properties;

    private static final Logger logger = LoggerFactory.getLogger(Configuration.class);

    private Configuration() {
        Main.securityLogger.info("create PropertiesApl");
        if (instance != null) {
            throw new AssertionError();
        }
    }

    public static Configuration getInstance() {
        Configuration localInstance = instance;
        if (localInstance == null) {
            synchronized (Configuration.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Configuration();
                    instance.init();
                }
            }
        }
        return localInstance;
    }

    private void init() {
        this.properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(PATH_TO_PROPERTIES)) {
            this.properties.load(fileInputStream);
        } catch (IOException e) {
            logger.error("Ошибка в программе: файл " + PATH_TO_PROPERTIES + " не обнаружено", e);
        }
    }

    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }

    public void renewDatabase(ConnectionManager connectionManager) throws SQLException {
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(DROP_TABLE_BRANDS +
                    DROP_TABLE_BRANDS_HISTORY +
                    CREATE_TABLE_BRANDS +
                    CREATE_TABLE_BRANDS_HISTORY);
        }
    }
}
