package com.taher.moneytransfer.dao;

import com.taher.moneytransfer.exception.RecordNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 *
 */
@Slf4j
public class DatabaseUtil {

    private static final String DB_USER = "sa";
    private static final String DB_PASS = "";
    private static final String DB_URL = "jdbc:h2:mem:money_transfer;DB_CLOSE_DELAY=-1;";
    private static final String CREATE_TABLE_SCRIPT = "INIT=runscript from 'classpath:/create-tables.sql'";
    private static final String SAMPLE_DATA_SCRIPT = "INIT=runscript from 'classpath:/data.sql'";
    private static final String CLEAR_DATA_SCRIPT = "INIT=runscript from 'classpath:/truncate.sql'";

    public static void initDB() throws SQLException {
        DriverManager.getConnection(DB_URL + CREATE_TABLE_SCRIPT, DB_USER, DB_PASS);
        log.info("database initialized.");
    }

    public static void insertSampleData() throws SQLException {
        DriverManager.getConnection(DB_URL + SAMPLE_DATA_SCRIPT, DB_USER, DB_PASS);
        log.info("sample data inserted.");
    }

    public static <E> E queryOne(Class<E> clazz, String query, Object... params) throws RecordNotFoundException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            QueryRunner runner = new QueryRunner();
            BeanHandler<E> handler = new BeanHandler<>(clazz);
            E result = runner.query(connection, query, handler, params);
            if (result == null) {
                log.debug("query with null result : {}", query);
                throw new RecordNotFoundException();
            }
            return result;
        } catch (SQLException e) {
            log.error("can not execute query : " + query, e);
            throw new IllegalStateException();
        }
    }

    public static <E> List<E> queryList(Class<E> clazz, String query, Object... params) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            QueryRunner runner = new QueryRunner();
            BeanListHandler<E> handler = new BeanListHandler<>(clazz);
            return runner.query(connection, query, handler, params);
        } catch (SQLException e) {
            log.error("can not execute query : " + query, e);
            throw new IllegalStateException();
        }
    }

    public static int queryUpdate(String query, Object... params) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            QueryRunner runner = new QueryRunner();
            return runner.update(connection, query, params);
        } catch (SQLException e) {
            log.error("can not execute query : " + query, e);
            throw new IllegalStateException();
        }
    }

    public static void clearDB() throws SQLException {
        DriverManager.getConnection(DB_URL + CLEAR_DATA_SCRIPT, DB_USER, DB_PASS);
        log.info("sample data inserted.");
    }
}