package ru.innopolis.lesson15;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.innopolis.lesson15.connection.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConfigurationTest {

    @Test
    @DisplayName("ТЕСТ RENEW_DATABASE МЕТОДА, КОГДА УСПЕШНОЕ ОБНОВЛЕНИЕ БД!")
    public void whenRenewDatabase() throws SQLException {
        ConnectionManager connectionManager = spy(ConnectionManager.class);
        Connection connection = spy(Connection.class);
        Statement statement = spy(Statement.class);
        when(connectionManager.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);
        Configuration.getInstance().renewDatabase(connectionManager);
        verify(connectionManager, times(1)).getConnection();
        verify(connection, times(1)).createStatement();
        verify(statement, times(1)).execute(Configuration.DROP_TABLE_BRANDS +
                Configuration.DROP_TABLE_BRANDS_HISTORY +
                Configuration.CREATE_TABLE_BRANDS +
                Configuration.CREATE_TABLE_BRANDS_HISTORY);
    }

    @Test
    @DisplayName("ТЕСТ GET_PROPERTIES МЕТОДА, КОГДА СУЩЕСТВУЮТ НЕОБХДИМЫЕ НАСТРОЙКИ!")
    public void whenGetProperties() {
        Configuration configuration = Configuration.getInstance();
        assertNotNull(configuration.getProperty("urlDB"));
        assertNotNull(configuration.getProperty("userDB"));
        assertNotNull(configuration.getProperty("passwordDB"));
        assertNull(configuration.getProperty("sdfas"));
    }

}