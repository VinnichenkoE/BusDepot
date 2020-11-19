package com.vinnichenko.bdepot.model.dao;

import com.vinnichenko.bdepot.exception.DaoException;
import com.vinnichenko.bdepot.model.entity.Order;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 * The interface Order dao.
 * Extends the interface of the {@code BaseDao}
 * Includes methods for the application to interact with Order objects in the database.
 */
public interface OrderDao extends BaseDao {
    /**
     * Find submitted orders.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Order> findSubmittedOrders() throws DaoException;

    /**
     * Find order by id.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Order> findOrderById(long id) throws DaoException;

    /**
     * Save order in the database.
     *
     * @param order      the order
     * @param connection the connection
     * @return the long
     * @throws DaoException the dao exception
     */
    long save(Order order, Connection connection) throws DaoException;

    /**
     * Save user order in the database.
     *
     * @param orderId    the order id
     * @param userId     the user id
     * @param connection the connection
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean saveUserOrder(long orderId, long userId, Connection connection) throws DaoException;

    /**
     * Find user orders.
     *
     * @param userId the user id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Order> findUserOrders(long userId) throws DaoException;

    /**
     * Update order status in the database.
     *
     * @param orderId    the order id
     * @param status     the status
     * @param connection the connection
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateOrderStatus(long orderId, Order.OrderStatus status, Connection connection) throws DaoException;

    /**
     * Find customer id.
     *
     * @param orderId    the order id
     * @param connection the connection
     * @return the long
     * @throws DaoException the dao exception
     */
    long findCustomerId(long orderId, Connection connection) throws DaoException;
}
