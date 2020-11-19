package com.vinnichenko.bdepot.model.service;

import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.model.entity.Bus;
import com.vinnichenko.bdepot.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface Bus service.
 * Indicates methods for processing information related to buses.
 */
public interface BusService {
    /**
     * Save.
     * Save a new bus in the database.
     *
     * @param parameters the parameters
     * @return the int
     * @throws ServiceException the service exception
     */
    int save(Map<String, String> parameters) throws ServiceException;

    /**
     * Find free buses by number seats.
     *
     * @param numberSeats the number seats
     * @param startDate   the start date
     * @param endDate     the end date
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Bus> findFreeBusesByNumberSeats(int numberSeats, long startDate, long endDate) throws ServiceException;

    /**
     * Find by user id.
     *
     * @param userId the user id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Bus> findByUserId(long userId) throws ServiceException;

    /**
     * Find all.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Bus> findAll() throws ServiceException;

    /**
     * Find all with driver.
     *
     * @return the map
     * @throws ServiceException the service exception
     */
    Map<Bus, User> findAllWithDriver() throws ServiceException;

    /**
     * Find by id.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Bus> findById(String id) throws ServiceException;

    /**
     * Appoint user.
     *
     * @param vehicleId the vehicle id
     * @param userId    the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean appointUser(String vehicleId, String userId) throws ServiceException;

    /**
     * Update.
     *
     * @param bus the bus
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean update(Bus bus) throws ServiceException;

    /**
     * Change bus status.
     *
     * @param busId    the bus id
     * @param statusId the status id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean changeBusStatus(String busId, String statusId) throws ServiceException;
}
