package ru.innopolis.lesson15.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import ru.innopolis.lesson15.connection.ConnectionManager;
import ru.innopolis.lesson15.entity.Brand;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class BrandsRepositoryTest {

    private static BrandsRepository brandsRepository;


    private static Brand brand;

    private static List<Brand> brands;


    @Mock
    private static ConnectionManager connectionManager;

    @Spy
    private Connection connection;

    @Spy
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @BeforeAll
    public static void beforeAll() {

        brand = new Brand("камера", 100, 100);
        brands = Arrays.asList(new Brand("регистратор", 100, 100),
                new Brand("камера", 101, 101));

    }

    @BeforeEach
    public void beforeEach() {
        initMocks(this);
        brandsRepository = new BrandsRepository(connectionManager);
    }

    @Test
    @DisplayName("ТЕСТ SAVE МЕТОДА, КОГДА УСПЕШНОЕ СОХРАНЕНИЕ!")
    public void whenSaveBrandOk() throws SQLException {
        when(connectionManager.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(BrandsRepository.INSERT_BRAND, Statement.RETURN_GENERATED_KEYS)).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(2);

        int result = brandsRepository.save(brand);

        verify(connectionManager, times(1)).getConnection();
        verify(connection, atMost(1)).prepareStatement(BrandsRepository.INSERT_BRAND);
        verify(preparedStatement, times(1)).setString(1, brand.getName());
        verify(preparedStatement, times(1)).setInt(2, brand.getPrice());
        verify(preparedStatement, times(1)).setInt(3, brand.getCount());
        verify(preparedStatement, times(1)).executeUpdate();

        assertEquals(2, result);
    }

    @Test
    @DisplayName("ТЕСТ SAVE МЕТОДА, КОГДА BRAND НЕ СОХРАНИЛСЯ!")
    public void whenNotSaveBrand() throws SQLException {
        when(connectionManager.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(BrandsRepository.INSERT_BRAND, Statement.RETURN_GENERATED_KEYS)).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(0);
        int result = brandsRepository.save(brand);
        assertEquals(0, result);
    }

    @Test
    @DisplayName("ТЕСТ SAVE_ALL МЕТОДА, КОГДА УСПЕШНОЕ СОХРАНЕНИЕ ВСЕХ BRANDS")
    public void whenSaveAllBrands() throws SQLException{
        when(connectionManager.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(BrandsRepository.INSERT_BRAND, new String[]{"id"})).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(1)).thenReturn(1).thenReturn(2);

        List<Integer> result = brandsRepository.saveAll(brands);

        verify(connectionManager, times(1)).getConnection();
        verify(connection, atMost(1)).prepareStatement(BrandsRepository.INSERT_BRAND, new String[]{"id"});
        for (Brand brand : brands) {
            verify(preparedStatement, times(1)).setString(1, brand.getName());
            verify(preparedStatement, times(1)).setInt(2, brand.getPrice());
            verify(preparedStatement, times(1)).setInt(3, brand.getCount());
        }
        verify(preparedStatement, times(2)).addBatch();
        verify(preparedStatement, times(1)).executeBatch();
        assertEquals(Arrays.asList(1, 2), result);
    }

    @Test
    @DisplayName("ТЕСТ UPDATE МЕТОДА, КОГДА УСПЕШНОЕ СОХРАНЕНИЕ ИЗМЕНЕНИЙ В BRAND")
    public void whenUpdateBrand() throws SQLException {
        brand.setId(3);
        when(connectionManager.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(BrandsRepository.GET_BRAND)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(connection.prepareStatement(BrandsRepository.INSERT_BRAND_HISTORY)).thenReturn(preparedStatement);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("name")).thenReturn(brand.getName());
        when(resultSet.getInt("price")).thenReturn(brand.getPrice());
        when(resultSet.getInt("count")).thenReturn(brand.getCount());
        when(resultSet.getInt("id")).thenReturn(brand.getId());
        when(connection.prepareStatement(BrandsRepository.UPDATE_BRAND)).thenReturn(preparedStatement);

        boolean result = brandsRepository.update(brand);

        verify(connectionManager, times(1)).getConnection();
        verify(connection, atMost(1)).prepareStatement(BrandsRepository.GET_BRAND);
        verify(preparedStatement, times(2)).setString(1, brand.getName());
        verify(preparedStatement, times(2)).setInt(2, brand.getPrice());
        verify(preparedStatement, times(2)).setInt(3, brand.getCount());
        verify(preparedStatement, times(2)).setInt(4, brand.getId());

        assertEquals(true, result);
    }
}
