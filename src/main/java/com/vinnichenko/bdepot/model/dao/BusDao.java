package com.vinnichenko.bdepot.model.dao;

import com.vinnichenko.bdepot.exception.DaoException;
import com.vinnichenko.bdepot.model.entity.Bus;
import com.vinnichenko.bdepot.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface Bus dao.
 * Extends the interface of the {@code BaseDao}.
 * Includes methods for the application to interact with Bus objects in the database.
 */
public interface BusDao extends BaseDao {
    /**
     * Save bus in the database.
     *
     * @param bus the bus
     * @return the int
     * @throws DaoException the dao exception
     */
    int save(Bus bus) throws DaoException;

    /**
     * Find free buses by number seats.
     *
     * @param numberOfSeats the number of seats
     * @param startDate     the start date
     * @param endDate       the end date
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Bus> findFreeBusesByNumberSeats(int numberOfSeats, long startDate, long endDate) throws DaoException;

    /**
     * Find by user id.
     *
     * @param userId the user id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Bus> findByUserId(long userId) throws DaoException;

    /**
     * Find all.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Bus> findAll() throws DaoException;

    /**
     * Find all with drivers.
     *
     * @return the map
     * @throws DaoException the dao exception
     */
    Map<Bus, User> findAllWithDrivers() throws DaoException;

    /**
     * Find by id.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Bus> findById(int id) throws DaoException;

    /**
     * Update bus in the database.
     *
     * @param bus the bus
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean update(Bus bus) throws DaoException;

    /**
     * Update bus status.
     *
     * @param userId the user id
     * @param status the status
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateBusStatus(long userId, Bus.BusStatus status) throws DaoException;
}
