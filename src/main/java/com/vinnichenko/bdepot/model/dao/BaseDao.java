package com.vinnichenko.bdepot.model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The interface Base dao.
 * Has default implementations of methods for closing a statement and resultSet.
 */
public interface BaseDao {
    /**
     * The constant logger.
     */
    Logger logger = LogManager.getLogger();

    /**
     * Close statement.
     *
     * @param statement the statement
     */
    default void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error("can't close statement", e);
            }
        }
    }

    /**
     * Close resultSet.
     *
     * @param resultSet the result set
     */
    default void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error("can't close resultSet", e);
            }
        }
    }
}
