package com.vinnichenko.bdepot.model.service;

import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.model.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> findSubmittedOrders() throws ServiceException;
    Optional<Order> findById(String id) throws ServiceException;
    long save(long userId, Order order) throws ServiceException;
    List<Order> findUserOrders(long userId) throws ServiceException;
    boolean appointUser(int userId, int bidId) throws ServiceException;
}
