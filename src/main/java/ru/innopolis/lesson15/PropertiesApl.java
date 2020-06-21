package ru.innopolis.lesson15;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesApl {

    private static final String PATH_TO_PROPERTIES = "src/main/resources/lesson15.properties";

    private static volatile PropertiesApl instance;

    private Properties properties;

    private static final Logger logger = LoggerFactory.getLogger(PropertiesApl.class);

    private PropertiesApl() {
        Main.securityLogger.info("create PropertiesApl");
        if (instance != null) {
            throw new AssertionError();
        }
    }

    public static PropertiesApl getInstance() {
        PropertiesApl localInstance = instance;
        if (localInstance == null) {
            synchronized (PropertiesApl.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new PropertiesApl();
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

    public String getProperty (String key) {
        return this.properties.getProperty(key);
    }
}
