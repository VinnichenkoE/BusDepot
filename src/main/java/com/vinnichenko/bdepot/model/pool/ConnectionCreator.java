package com.vinnichenko.bdepot.model.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

class ConnectionCreator {

    private static final Logger logger = LogManager.getLogger();

    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc.url";
    private static final String DATABASE_USERNAME = "jdbc.username";
    private static final String DATABASE_PASSWORD = "jdbc.password";
    private static final String RESOURCE_NAME = "db";

    static {
        try {
            Class.forName(DATABASE_DRIVER);
        } catch (ClassNotFoundException e) {
            logger.fatal("Driver not registered", e);
            throw new RuntimeException("Driver not registered", e);
        }
    }

    static ProxyConnection create() {
        Connection connection;
        ProxyConnection proxyConnection;
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_NAME);
        String url = bundle.getString(DATABASE_URL);
        String login = bundle.getString(DATABASE_USERNAME);
        String pass = bundle.getString(DATABASE_PASSWORD);
        try {
            connection = DriverManager.getConnection(url, login, pass);
        } catch (SQLException e) {
            logger.fatal("Database access error", e);
            throw new RuntimeException("Database access error", e);
        }
        proxyConnection = new ProxyConnection(connection);
        return proxyConnection;
    }

    static void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("Driver has not been deregistered", e);
            }
        });
    }
}
