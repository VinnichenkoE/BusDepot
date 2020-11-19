package com.vinnichenko.bdepot.model.dao.impl;

import com.vinnichenko.bdepot.exception.DaoException;
import com.vinnichenko.bdepot.model.dao.TripDao;
import com.vinnichenko.bdepot.model.entity.Trip;
import com.vinnichenko.bdepot.model.pool.ConnectionPool;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.vinnichenko.bdepot.model.dao.ColumnLabel.*;

/**
 * The type Trip dao.
 */
public class TripDaoImpl implements TripDao {

    private static final String SQL_SAVE = "INSERT INTO trips (start_date, end_date, cost, order_id_fk, user_id_fk) " +
            "VALUES (?, ?, ? ,?, ?);";
    private static final String SQL_FIND_BY_USER_ID = "SELECT trip_id, start_date, end_date, cost, order_id_fk, " +
            "user_id_fk FROM trips WHERE user_id_fk = ?;";
    private static final String SQL_FIND_BY_ID = "SELECT trip_id, start_date, end_date, cost, order_id_fk, user_id_fk " +
            "FROM trips WHERE trip_id = ?";
    private static final String SQL_UPDATE = "UPDATE trips SET start_date = ?, end_date = ?, cost = ?, " +
            "order_id_fk = ?, user_id_fk = ? WHERE trip_id = ?;";
    private ConnectionPool pool = ConnectionPool.INSTANCE;

    @Override
    public long save(Trip trip, Connection connection) throws DaoException {
        PreparedStatement insertTripStatement = null;
        ResultSet resultSet = null;
        long id = -1L;
        try {
            insertTripStatement = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS);
            insertTripStatement.setLong(1, trip.getStartDate());
            insertTripStatement.setLong(2, trip.getEndDate());
            insertTripStatement.setBigDecimal(3, trip.getCost());
            insertTripStatement.setLong(4, trip.getOrderId());
            insertTripStatement.setLong(5, trip.getUserId());
            insertTripStatement.executeUpdate();
            resultSet = insertTripStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new DaoException("Save trip error", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(insertTripStatement);
        }
        return id;
    }

    @Override
    public List<Trip> findByUserId(long userId) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Trip> trips = new ArrayList<>();
        Trip trip;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FIND_BY_USER_ID);
            statement.setLong(1, userId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                trip = tripFromResultSet(resultSet);
                trips.add(trip);
            }
        } catch (SQLException e) {
            throw new DaoException("Find trips by user id error", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return trips;
    }

    @Override
    public Optional<Trip> findById(long tripId) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Trip trip = null;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setLong(1, tripId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                trip = tripFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Find trip by id error ", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return Optional.ofNullable(trip);
    }

    @Override
    public boolean update(Trip trip, Connection connection) throws DaoException {
        PreparedStatement statement = null;
        boolean result;
        try {
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setLong(1, trip.getStartDate());
            statement.setLong(2, trip.getEndDate());
            statement.setBigDecimal(3, trip.getCost());
            statement.setLong(4, trip.getOrderId());
            statement.setLong(5, trip.getUserId());
            statement.setLong(6, trip.getTripId());
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Update trip error", e);
        } finally {
            closeStatement(statement);
        }
        return result;
    }

    private Trip tripFromResultSet(ResultSet resultSet) throws SQLException {
        long tripId = resultSet.getLong(TRIP_ID);
        long startDate = resultSet.getLong(TRIPS_START_DATE);
        long endDate = resultSet.getLong(TRIPS_END_DATE);
        BigDecimal cost = resultSet.getBigDecimal(COST);
        long orderId = resultSet.getLong(TRIPS_ORDER_ID);
        long userId = resultSet.getLong(TRIPS_USER_ID);
        Trip trip = new Trip(tripId, startDate, endDate, cost, orderId, userId);
        return trip;
    }
}
