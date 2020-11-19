package com.vinnichenko.bdepot.model.dao;

import com.vinnichenko.bdepot.exception.DaoException;
import com.vinnichenko.bdepot.model.entity.Trip;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 * The interface Trip dao.
 * Extends the interface of the {@code BaseDao}
 * Includes methods for the application to interact with Trip objects in the database.
 */
public interface TripDao extends BaseDao {
    /**
     * Save trip in the database.
     *
     * @param trip       the trip
     * @param connection the connection
     * @return the long
     * @throws DaoException the dao exception
     */
    long save(Trip trip, Connection connection) throws DaoException;

    /**
     * Find by user id.
     *
     * @param userId the user id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Trip> findByUserId(long userId) throws DaoException;

    /**
     * Find by trip id.
     *
     * @param tripId the trip id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Trip> findById(long tripId) throws DaoException;

    /**
     * Update trip in the database.
     *
     * @param trip       the trip
     * @param connection the connection
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean update(Trip trip, Connection connection) throws DaoException;
}
