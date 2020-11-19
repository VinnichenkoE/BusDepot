package com.vinnichenko.bdepot.model.dao;

import com.vinnichenko.bdepot.exception.DaoException;
import com.vinnichenko.bdepot.exception.TransactionException;
import com.vinnichenko.bdepot.model.entity.Bill;
import com.vinnichenko.bdepot.model.entity.Order;
import com.vinnichenko.bdepot.model.entity.Trip;
import com.vinnichenko.bdepot.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The type Transaction manager.
 */
public class TransactionManager {
    private static final TransactionManager INSTANCE = new TransactionManager();
    private static final Logger LOGGER = LogManager.getLogger();

    private TransactionManager() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static TransactionManager getInstance() {
        return INSTANCE;
    }

    /**
     * Start trip.
     *
     * @param trip the trip
     * @return the boolean
     * @throws TransactionException the transaction exception
     */
    public boolean startTrip(Trip trip) throws TransactionException {
        Connection connection = null;
        OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
        TripDao tripDao = DaoFactory.getInstance().getTripDao();
        boolean result;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);
            result = tripDao.save(trip, connection) > 0;
            if (result) {
                result = orderDao.updateOrderStatus(trip.getOrderId(), Order.OrderStatus.IN_PROCESS, connection);
                connection.commit();
            }
        } catch (SQLException | DaoException e) {
            rollbackConnection(connection);
            throw new TransactionException("Error while process save trip", e);
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    /**
     * Finish trip.
     *
     * @param trip the trip
     * @return the boolean
     * @throws TransactionException the transaction exception
     */
    public boolean finishTrip(Trip trip) throws TransactionException {
        Connection connection = null;
        OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
        TripDao tripDao = DaoFactory.getInstance().getTripDao();
        BillDao billDao = DaoFactory.getInstance().getBillDao();
        boolean result;
        long customerId;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);
            result = tripDao.update(trip, connection);
            if (result) {
                result = orderDao.updateOrderStatus(trip.getOrderId(), Order.OrderStatus.COMPLETED, connection);
            }
            if (result) {
                customerId = orderDao.findCustomerId(trip.getOrderId(), connection);
                Bill bill = new Bill(trip.getCost(), (byte) 0, trip.getOrderId(), customerId);
                result = billDao.save(bill, connection) > 0;
                connection.commit();
            }
        } catch (SQLException | DaoException e) {
            rollbackConnection(connection);
            throw new TransactionException("Error while process update trip", e);
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    /**
     * Add order.
     *
     * @param order  the order
     * @param userId the user id
     * @return the long
     * @throws TransactionException the transaction exception
     */
    public long addOrder(Order order, long userId) throws TransactionException {
        Connection connection = null;
        long id;
        OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);
            id = orderDao.save(order, connection);
            if (id > 0) {
                orderDao.saveUserOrder(id, userId, connection);
                connection.commit();
            }
        } catch (SQLException | DaoException e) {
            rollbackConnection(connection);
            throw new TransactionException("Error while process adding order", e);
        } finally {
            closeConnection(connection);
        }
        return id;
    }

    /**
     * Appoint driver.
     *
     * @param userId  the user id
     * @param orderId the order id
     * @return the boolean
     * @throws TransactionException the transaction exception
     */
    public boolean appointDriver(long userId, long orderId) throws TransactionException {
        Connection connection = null;
        boolean result;
        OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);
            result = orderDao.saveUserOrder(orderId, userId, connection);
            if (result) {
                result = orderDao.updateOrderStatus(orderId, Order.OrderStatus.PENDING, connection);
                connection.commit();
            }
        } catch (DaoException | SQLException e) {
            rollbackConnection(connection);
            throw new TransactionException("Error while process appointing order", e);
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    private void rollbackConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            LOGGER.error("Error while rollback transaction", e);
        }
    }

    private void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Error while closing connection", e);
            }
        }
    }
}
