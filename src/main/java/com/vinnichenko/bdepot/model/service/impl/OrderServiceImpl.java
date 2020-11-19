package com.vinnichenko.bdepot.model.service.impl;

import com.vinnichenko.bdepot.exception.DaoException;
import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.exception.TransactionException;
import com.vinnichenko.bdepot.model.creator.OrderCreator;
import com.vinnichenko.bdepot.model.dao.DaoFactory;
import com.vinnichenko.bdepot.model.dao.OrderDao;
import com.vinnichenko.bdepot.model.dao.TransactionManager;
import com.vinnichenko.bdepot.model.entity.Order;
import com.vinnichenko.bdepot.model.service.OrderService;
import com.vinnichenko.bdepot.validator.DataValidator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.vinnichenko.bdepot.controller.RequestParameter.USER_ID;

/**
 * The type Order service.
 */
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
    public long save(Map<String, String> parameters) throws ServiceException {
        TransactionManager transactionManager = TransactionManager.getInstance();
        long id = -1L;
        if (DataValidator.checkOrderData(parameters)) {
            Order order = OrderCreator.createOrder(parameters);
            long userId = Long.parseLong(parameters.get(USER_ID));
            try {
                id = transactionManager.addOrder(order, userId);
            } catch (TransactionException e) {
                throw new ServiceException("Add order error", e);
            }
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
    public boolean appointDriver(String stringUserId, String stringOrderId) throws ServiceException {
        TransactionManager transactionManager = TransactionManager.getInstance();
        boolean result = false;
        if (DataValidator.isNumber(stringUserId) && DataValidator.isNumber(stringOrderId)) {
            long userId = Long.parseLong(stringUserId);
            long orderId = Long.parseLong(stringOrderId);
            try {
                result = transactionManager.appointDriver(userId, orderId);
            } catch (TransactionException e) {
                throw new ServiceException("Appoint user error", e);
            }
        }
        return result;
    }
}
