package com.vinnichenko.bdepot.model.dao;

import com.vinnichenko.bdepot.exception.DaoException;
import com.vinnichenko.bdepot.model.entity.Order;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface OrderDao extends BaseDao {
    List<Order> findSubmittedOrders() throws DaoException;

    Optional<Order> findOrderById(long id) throws DaoException;

    long save(Order order, Connection connection) throws DaoException;

    boolean saveUserOrder(long orderId, long userId, Connection connection) throws DaoException;

    List<Order> findUserOrders(long userId) throws DaoException;

    boolean updateOrderStatus(long orderId, Order.OrderStatus status, Connection connection) throws DaoException;

    long findCustomerId(long orderId, Connection connection) throws DaoException;
}
