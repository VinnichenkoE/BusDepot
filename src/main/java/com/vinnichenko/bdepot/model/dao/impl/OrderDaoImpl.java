package com.vinnichenko.bdepot.model.dao.impl;

import com.vinnichenko.bdepot.exception.DaoException;
import com.vinnichenko.bdepot.model.dao.OrderDao;
import com.vinnichenko.bdepot.model.entity.Order;
import com.vinnichenko.bdepot.model.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.vinnichenko.bdepot.model.dao.ColumnLabel.*;

public class OrderDaoImpl implements OrderDao {

    private static final String SQL_SUBMITTED_ORDERS = "SELECT order_id, number_of_seats, start_date, end_date, " +
            "start_point, end_point, distance, order_status_id_fk FROM orders WHERE order_status_id_fk = 0;";
    private static final String SQL_FIND_BY_ID = "SELECT order_id, number_of_seats, start_date, end_date, " +
            "start_point, end_point, distance, order_status_id_fk FROM orders WHERE order_id = ?;";
    private static final String SQL_SAVE_ORDER = "INSERT INTO orders (number_of_seats, start_date, end_date, " +
            "start_point, end_point, distance, order_status_id_fk) VALUES (?, ?, ?, ?, ?, ?, 0);";
    private static final String SQL_SAVE_USER_ORDER = "INSERT INTO user_orders (user_id_fk, order_id_fk) VALUES (?, ?);";
    private static final String SQL_UPDATE_STATUS = "UPDATE orders SET order_status_id_fk = ? WHERE order_id = ?;";
    private static final String SQL_FIND_USER_ORDERS = "SELECT o.order_id, o.number_of_seats, o.start_date, " +
            "o.end_date, o.start_point, o.end_point, o.distance, o.order_status_id_fk FROM orders o JOIN user_orders u " +
            "ON o.order_id = u.order_id_fk WHERE u.user_id_fk = ?;";
    private static final String SQL_FIND_CUSTOMER_ID = "SELECT uo.user_id_fk FROM user_orders uo JOIN users u " +
            "ON uo.user_id_fk = u.user_id WHERE u.role_id_fk = 2 AND order_id_fk = ?;";
    private ConnectionPool pool = ConnectionPool.INSTANCE;

    @Override
    public List<Order> findSubmittedOrders() throws DaoException {
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<Order> orders = new ArrayList<>();
        try {
            connection = pool.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SUBMITTED_ORDERS);
            while (resultSet.next()) {
                Order order = orderFromResultSet(resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException("Find submitted orders error", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return orders;
    }

    @Override
    public Optional<Order> findOrderById(long id) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Order order = null;
        Optional<Order> result;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order = orderFromResultSet(resultSet);
            }
            result = Optional.ofNullable(order);
        } catch (SQLException e) {
            throw new DaoException("Find order by id error", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(preparedStatement);
            pool.releaseConnection(connection);
        }
        return result;
    }

    @Override
    public long save(Order order, Connection connection) throws DaoException {
        PreparedStatement insertOrderStatement = null;
        ResultSet resultSet = null;
        long id = -1;
        try {
            insertOrderStatement = connection.prepareStatement(SQL_SAVE_ORDER, Statement.RETURN_GENERATED_KEYS);
            insertOrderStatement.setInt(1, order.getNumberOfSeats());
            insertOrderStatement.setLong(2, order.getStartDate());
            insertOrderStatement.setLong(3, order.getEndDate());
            insertOrderStatement.setString(4, order.getStartPoint());
            insertOrderStatement.setString(5, order.getEndPoint());
            insertOrderStatement.setInt(6, order.getDistance());
            insertOrderStatement.executeUpdate();
            resultSet = insertOrderStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new DaoException("Save order error", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(insertOrderStatement);
        }
        return id;
    }

    public boolean saveUserOrder(long orderId, long userId, Connection connection) throws DaoException {
        PreparedStatement insertUserOrderStatement = null;
        boolean result;
        try {
            insertUserOrderStatement = connection.prepareStatement(SQL_SAVE_USER_ORDER);
            insertUserOrderStatement.setLong(1, userId);
            insertUserOrderStatement.setLong(2, orderId);
            result = insertUserOrderStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Save user order error", e);
        } finally {
            closeStatement(insertUserOrderStatement);
        }
        return result;
    }

    @Override
    public List<Order> findUserOrders(long userId) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Order order;
        List<Order> orders = new ArrayList<>();
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_USER_ORDERS);
            preparedStatement.setLong(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                order = orderFromResultSet(resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException("Find user orders error", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(preparedStatement);
            pool.releaseConnection(connection);
        }
        return orders;
    }

    @Override
    public boolean updateOrderStatus(long orderId, Order.OrderStatus status, Connection connection)
            throws DaoException {
        PreparedStatement preparedStatement = null;
        boolean result;
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_STATUS);
            preparedStatement.setInt(1, status.ordinal());
            preparedStatement.setLong(2, orderId);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Update order status error", e);
        } finally {
            closeStatement(preparedStatement);
        }
        return result;
    }

    @Override
    public long findCustomerId(long orderId, Connection connection) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long id = -1L;
        try {
            statement = connection.prepareStatement(SQL_FIND_CUSTOMER_ID);
            statement.setLong(1, orderId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new DaoException("Find customer id error", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return id;
    }

    private Order orderFromResultSet(ResultSet resultSet) throws SQLException {
        long orderId = resultSet.getInt(ORDER_ID);
        int numberOfSeats = resultSet.getInt(NUMBER_OF_SEATS);
        long startDate = resultSet.getLong(START_DATE);
        long endDate = resultSet.getLong(END_DATE);
        String startPoint = resultSet.getString(START_POINT);
        String endPoint = resultSet.getString(END_POINT);
        int distance = resultSet.getInt(DISTANCE);
        int statusId = resultSet.getInt(ORDERS_ORDER_STATUS_ID);
        Order order = new Order(orderId, numberOfSeats, startDate, endDate, startPoint, endPoint, distance,
                Order.OrderStatus.values()[statusId]);
        return order;
    }
}