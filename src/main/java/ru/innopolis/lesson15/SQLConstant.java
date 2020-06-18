package ru.innopolis.lesson15;

public class SQLConstant {
    public static final String INSERT_BRAND = "INSERT INTO brands (name, price, count) VALUES (?, ?, ?);";
    public static final String UPDATE_BRAND = "UPDATE brands SET name = ?, price = ?, count = ? WHERE id = ?";
    public static final String INSERT_BRAND_HISTORY = "INSERT INTO brands_history (name, price, count, brand_id) VALUES (?, ?, ?, ?);";
    public static final String GET_BRAND = "SELECT id, name, price, count FROM brands WHERE id = ?";
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
}
