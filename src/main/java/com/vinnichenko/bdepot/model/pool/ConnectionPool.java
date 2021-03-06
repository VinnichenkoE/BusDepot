package com.vinnichenko.bdepot.model.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * The enum Connection pool.
 * Provides connection of the application to the database.
 */
public enum ConnectionPool {

    /**
     * Instance connection pool.
     */
    INSTANCE;

    private static final Logger logger = LogManager.getLogger();
    private static final int POOL_SIZE = 32;
    private final BlockingDeque<ProxyConnection> freeConnections;
    private final Queue<ProxyConnection> givenConnections;

    ConnectionPool() {
        freeConnections = new LinkedBlockingDeque<>(POOL_SIZE);
        givenConnections = new ArrayDeque<>();
        for (int i = 0; i < POOL_SIZE; i++) {
            ProxyConnection connection = ConnectionCreator.create();
            freeConnections.offer(connection);
        }
    }

    /**
     * Gets connection.
     * Issues a free connection from the blocking queue of free connections.
     *
     * @return the connection
     */
    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            givenConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.warn("Thread was interrupted", e);
        }
        return connection;
    }

    /**
     * Release connection.
     * Returns a previously given connection from the given connections queue.
     *
     * @param connection the connection
     */
    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection && givenConnections.remove(connection)) {
            freeConnections.offer((ProxyConnection) connection);
        } else {
            logger.error("Connection is invalid: {}", connection);
        }
    }

    /**
     * Destroy pool.
     */
    public void destroyPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (SQLException e) {
                logger.error("Connection was not deleted", e);
            } catch (InterruptedException e) {
                logger.warn("Thread was interrupted", e);
            }
        }
        ConnectionCreator.deregisterDrivers();
    }
}