package com.vinnichenko.bdepot.model.dao;

import com.vinnichenko.bdepot.exception.DaoException;
import com.vinnichenko.bdepot.model.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao extends BaseDao {
    List<Order> findSubmittedOrders() throws DaoException;

    Optional<Order> findOrderById(long id) throws DaoException;

    long save(Order order, long userId) throws DaoException;

    boolean appointUser(long userId, long orderId) throws DaoException;

    List<Order> findUserOrders(long userId) throws DaoException;

    boolean updateOrderStatus(long orderId, Order.OrderStatus status) throws DaoException;

    long findCustomerId(long orderId) throws DaoException;
}
