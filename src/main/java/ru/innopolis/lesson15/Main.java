package ru.innopolis.lesson15;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.lesson15.connection.ConnectionManager;
import ru.innopolis.lesson15.connection.ConnectionManagerJDBCImpl;
import ru.innopolis.lesson15.entity.Brand;
import ru.innopolis.lesson15.repository.BrandsRepository;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static final Logger securityLogger = LoggerFactory.getLogger("security");

    public static final Logger analyticsLogger = LoggerFactory.getLogger("analytics");

    public static void main(String[] args) throws SQLException {
        Configuration configuration = Configuration.getInstance();
        ConnectionManager connectionManager = ConnectionManagerJDBCImpl.getInstance();
        configuration.renewDatabase(connectionManager);
        BrandsRepository repository = new BrandsRepository(connectionManager);
        List<Brand> brands = Arrays.asList(new Brand("регистратор", 20000, 10), new Brand("камера", 1000, 777));
        repository.saveAll(brands);
        Brand brand = new Brand("камера", 10000, 100);
        brand.setId(repository.save(brand));
        brand.setCount(77);
        brand.setPrice(9977);
        repository.update(brand);
    }
}
