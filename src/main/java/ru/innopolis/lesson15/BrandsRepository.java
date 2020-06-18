package ru.innopolis.lesson15;

import ru.innopolis.lesson02.task01.UserRuntimeException;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class BrandsRepository {

    public static void main(String[] args) throws SQLException {
        PropertiesApl prop = PropertiesApl.getInstance();
        DBUtil.renewDatabase();
        BrandsRepository repository = new BrandsRepository();
        Brand brand = new Brand("камера", 10000, 100);
        brand.setId(repository.save(brand));
        List<Brand> brands = Arrays.asList(new Brand("регистратор", 20000, 10), new Brand("камера", 1000, 777));
        repository.saveAll(brands);
        brand.setCount(77);
        brand.setPrice(9977);
        repository.update(brand);
    }


    public int save(Brand brand) {
        int index = -1;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQLConstant.INSERT_BRAND)) {
            this.fillStatementBrand(preparedStatement, brand);
            index = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return index;
    }

    public int[] saveAll(List<Brand> brands) {
        int[] indexes = new int[]{-1};
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQLConstant.INSERT_BRAND)) {
            for (Brand brand : brands) {
                this.fillStatementBrand(preparedStatement, brand);
                preparedStatement.addBatch();
            }
            indexes = preparedStatement.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return indexes;
    }

    public boolean update(Brand brand) {
        boolean result = true;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statementOldBrand = connection.prepareStatement(SQLConstant.GET_BRAND)) {
            connection.setAutoCommit(false);
            statementOldBrand.setInt(1, brand.getId());
            Savepoint point = connection.setSavepoint("savepoint");;
            try (ResultSet resultOldBrand = statementOldBrand.executeQuery();
                 PreparedStatement statementHistory = connection.prepareStatement(SQLConstant.INSERT_BRAND_HISTORY);) {
                this.fillStatementHistory(resultOldBrand, statementHistory);
                statementHistory.executeUpdate();
            }
            try (PreparedStatement statementBrand = connection.prepareStatement(SQLConstant.UPDATE_BRAND)) {
                fillStatementBrand(statementBrand, brand);
                statementBrand.setInt(4, brand.getId());
                statementBrand.executeUpdate();
                //throw new UserRuntimeException("error");
            } catch (Exception e) {
                e.printStackTrace();
                connection.rollback(point);
                result = false;
            }
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    private void fillStatementHistory(ResultSet resultSet, PreparedStatement preparedStatement) throws SQLException {
        if (resultSet.next()) {
            preparedStatement.setString(1, resultSet.getString("name"));
            preparedStatement.setInt(2, resultSet.getInt("price"));
            preparedStatement.setInt(3, resultSet.getInt("count"));
            preparedStatement.setInt(4, resultSet.getInt("id"));
        }
    }

    private void fillStatementBrand(PreparedStatement preparedStatement, Brand brand) throws SQLException {
        preparedStatement.setString(1, brand.getName());
        preparedStatement.setInt(2, brand.getPrice());
        preparedStatement.setInt(3, brand.getCount());
    }
}
