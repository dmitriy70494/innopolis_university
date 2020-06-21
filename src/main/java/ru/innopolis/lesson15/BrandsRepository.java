package ru.innopolis.lesson15;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BrandsRepository {

    private static final Logger logger = LoggerFactory.getLogger(BrandsRepository.class);

    private static final BrandsRepository instance = new BrandsRepository();

    private BrandsRepository() {
        Main.securityLogger.info("create BrandsRepository");
    }

    public static BrandsRepository getInstance() {
        return instance;
    }

    public int save(Brand brand) {
        int result = 0;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SQLConstant.INSERT_BRAND, Statement.RETURN_GENERATED_KEYS)) {
            this.fillStatementBrand(preparedStatement, brand);
            result = preparedStatement.executeUpdate();
            if (result == 0) {
                throw new SQLException("Creating brand failed, no rows.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    result = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (Exception e) {
            logger.error("error save brand BrandsRepository#save", e);
        }
        Main.analyticsLogger.info("Brand save, id= " + result);
        return result;
    }

    public List<Integer> saveAll(List<Brand> brands) {
        List<Integer> ids = new ArrayList<>();
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQLConstant.INSERT_BRAND, new String[]{"id"})) {
            for (Brand brand : brands) {
                this.fillStatementBrand(preparedStatement, brand);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                while (generatedKeys.next()) {
                    ids.add(generatedKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            logger.error("error save brands BrandsRepository#saveAll", e);
        }
        Main.analyticsLogger.info("brands saveAll ids= " + ids);
        return ids;
    }

    public boolean update(Brand brand) {
        boolean result = true;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statementOldBrand = connection.prepareStatement(SQLConstant.GET_BRAND)) {
            connection.setAutoCommit(false);
            statementOldBrand.setInt(1, brand.getId());
            Savepoint point = connection.setSavepoint("savepoint");
            try (ResultSet resultOldBrand = statementOldBrand.executeQuery();
                 PreparedStatement statementHistory = connection.prepareStatement(SQLConstant.INSERT_BRAND_HISTORY);) {
                this.fillStatementHistory(resultOldBrand, statementHistory);
                statementHistory.executeUpdate();
            }
            try (PreparedStatement statementBrand = connection.prepareStatement(SQLConstant.UPDATE_BRAND)) {
                fillStatementBrand(statementBrand, brand);
                statementBrand.setInt(4, brand.getId());
                statementBrand.executeUpdate();
            } catch (Exception e) {
                logger.error("error update brand BrandsRepository#update", e);
                connection.rollback(point);
                result = false;
            }
            connection.commit();
        } catch (Exception e) {
            logger.error("error update brand or brand_history BrandsRepository#update", e);
            result = false;
        }
        Main.analyticsLogger.info("update brand id= " + brand.getId());
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
