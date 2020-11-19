package com.vinnichenko.bdepot.model.dao.impl;

import com.vinnichenko.bdepot.exception.DaoException;
import com.vinnichenko.bdepot.model.dao.BillDao;
import com.vinnichenko.bdepot.model.entity.Bill;

import java.sql.*;

/**
 * The type Bill dao.
 */
public class BillDaoImpl implements BillDao {

    private static final String SQL_SAVE = "INSERT INTO bills (cost, is_payed, order_id_fk, user_id_fk) " +
            "VALUES (?, ?, ?, ?);";

    @Override
    public long save(Bill bill, Connection connection) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long id = -1L;
        try {
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
        }
        return id;
    }
}
