package com.vinnichenko.bdepot.model.service;

import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.model.entity.Order;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrderService {
    List<Order> findSubmittedOrders() throws ServiceException;

    Optional<Order> findById(String id) throws ServiceException;

    long save(Map<String, String> parameters) throws ServiceException;

    List<Order> findUserOrders(long userId) throws ServiceException;

    boolean appointDriver(String userId, String bidId) throws ServiceException;
}
