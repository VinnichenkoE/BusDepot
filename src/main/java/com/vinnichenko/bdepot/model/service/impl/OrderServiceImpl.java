package com.vinnichenko.bdepot.model.service.impl;

import com.vinnichenko.bdepot.exception.DaoException;
import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.model.dao.DaoFactory;
import com.vinnichenko.bdepot.model.dao.OrderDao;
import com.vinnichenko.bdepot.model.entity.Order;
import com.vinnichenko.bdepot.model.service.OrderService;
import com.vinnichenko.bdepot.validator.DataValidator;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    @Override
    public List<Order> findSubmittedOrders() throws ServiceException {
        OrderDao dao = DaoFactory.getInstance().getOrderDao();
        List<Order> submittedOrders;
        try {
            submittedOrders = dao.findSubmittedOrders();
        } catch (DaoException e) {
            throw new ServiceException("Find submitted orders error", e);
        }
        return submittedOrders;
    }

    @Override
    public Optional<Order> findById(String id) throws ServiceException {
        OrderDao dao = DaoFactory.getInstance().getOrderDao();
        Optional<Order> order = Optional.empty();
        try {
            if (DataValidator.isNumber(id)) {
                order = dao.findOrderById(Long.parseLong(id));
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return order;
    }

    @Override
    public long save(long userId, Order order) throws ServiceException {
        OrderDao dao = DaoFactory.getInstance().getOrderDao();
        long id;
        try {
            id = dao.save(order, userId);
        } catch (DaoException e) {
            throw new ServiceException("Save order error", e);
        }
        return id;
    }

    @Override
    public List<Order> findUserOrders(long userId) throws ServiceException {
        OrderDao dao = DaoFactory.getInstance().getOrderDao();
        List<Order> orders;
        try {
            orders = dao.findUserOrders(userId);
        } catch (DaoException e) {
            throw new ServiceException("Find user orders error", e);
        }
        return orders;
    }

    @Override
    public boolean appointUser(int userId, int bidId) throws ServiceException {
        OrderDao dao = DaoFactory.getInstance().getOrderDao();
        boolean result;
        try {
            result = dao.appointUser(userId, bidId);
        } catch (DaoException e) {
            throw new ServiceException("Appoint user error", e);
        }
        return result;
    }
}
