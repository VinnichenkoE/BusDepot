package com.vinnichenko.bdepot.model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface BaseDao {
    Logger logger = LogManager.getLogger();

    default void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error("can't close statement", e);
            }
        }
    }

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
