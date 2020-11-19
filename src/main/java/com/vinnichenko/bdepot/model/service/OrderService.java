package com.vinnichenko.bdepot.model.service;

import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.model.entity.Order;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface Order service.
 * Indicates methods for processing information related to orders.
 */
public interface OrderService {
    /**
     * Find submitted orders.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findSubmittedOrders() throws ServiceException;

    /**
     * Find by id.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Order> findById(String id) throws ServiceException;

    /**
     * Save.
     * Saves a new order in the database.
     *
     * @param parameters the parameters
     * @return the long
     * @throws ServiceException the service exception
     */
    long save(Map<String, String> parameters) throws ServiceException;

    /**
     * Find user orders.
     *
     * @param userId the user id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findUserOrders(long userId) throws ServiceException;

    /**
     * Appoint driver.
     *
     * @param userId the user id
     * @param bidId  the bid id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean appointDriver(String userId, String bidId) throws ServiceException;
}
