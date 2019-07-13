package com.taher.moneytransfer;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 */
public class DatabaseUtil {

    private static final String DB_USER = "sa";
    private static final String DB_PASS = "";
    private static final String DB_URL = "jdbc:h2:mem:money_transfer;DB_CLOSE_DELAY=-1;";
    private static final String CREATE_TABLE_SCRIPT = "INIT=runscript from 'classpath:/create-tables.sql'";
    private static final String SAMPLAE_DATA_SCRIPT = "INIT=runscript from 'classpath:/data.sql'";

    public static void initDB() {
        try (Connection connection = DriverManager.getConnection(DB_URL + CREATE_TABLE_SCRIPT, DB_USER, DB_PASS)) {
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public static void insertSampleData() {
        try (Connection connection = DriverManager.getConnection(DB_URL + SAMPLAE_DATA_SCRIPT, DB_USER, DB_PASS)) {
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public static <E> Optional<E> queryOne(Class<E> clazz, String query, Object... params) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            QueryRunner runner = new QueryRunner();
            BeanHandler<E> handler = new BeanHandler<>(clazz);
            return Optional.ofNullable(runner.query(connection, query, handler, params));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public static <E> List<E> queryList(Class<E> clazz, String query, Object... params) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            QueryRunner runner = new QueryRunner();
            BeanListHandler<E> handler = new BeanListHandler<>(clazz);
            return runner.query(connection, query, handler, params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public static int queryUpdate(String query, Object... params) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            QueryRunner runner = new QueryRunner();
            return runner.update(connection, query, params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
}