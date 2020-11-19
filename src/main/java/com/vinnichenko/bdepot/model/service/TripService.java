package com.vinnichenko.bdepot.model.service;

import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.model.entity.Trip;

import java.util.List;

/**
 * The interface Trip service.
 * Indicates methods for processing information related to trips.
 */
public interface TripService {
    /**
     * Start trip.
     * Adds a new trip in the database and update the order status.
     *
     * @param userId  the user id
     * @param orderId the order id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean startTrip(long userId, long orderId) throws ServiceException;

    /**
     * Find by user id.
     *
     * @param userId the user id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Trip> findByUserId(long userId) throws ServiceException;

    /**
     * Finish trip.
     * Updates the trip and update the order status.
     *
     * @param tripId the trip id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean finishTrip(String tripId) throws ServiceException;
}
