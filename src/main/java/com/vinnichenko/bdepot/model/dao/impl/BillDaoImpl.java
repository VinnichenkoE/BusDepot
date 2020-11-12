package com.vinnichenko.bdepot.model.dao.impl;

import com.vinnichenko.bdepot.exception.DaoException;
import com.vinnichenko.bdepot.model.dao.BillDao;
import com.vinnichenko.bdepot.model.entity.Bill;
import com.vinnichenko.bdepot.model.pool.ConnectionPool;

import java.sql.*;

public class BillDaoImpl implements BillDao {

    private static final String SQL_SAVE = "INSERT INTO bills (cost, is_payed, order_id_fk, user_id_fk) VALUES (?, ?, ?, ?);";
    private ConnectionPool pool = ConnectionPool.INSTANCE;

    @Override
    public long save(Bill bill) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long id = -1L;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS);
            statement.setBigDecimal(1, bill.getCost());
            statement.setByte(2, (byte)0);
            statement.setLong(3, bill.getOrderId());
            statement.setLong(4, bill.getUserId());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new DaoException("Save bill error", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return id;
    }
}
