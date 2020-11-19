package com.vinnichenko.bdepot.model.dao;

import com.vinnichenko.bdepot.exception.DaoException;
import com.vinnichenko.bdepot.model.entity.Bill;

import java.sql.Connection;

/**
 * The interface Bill dao.
 */
public interface BillDao extends BaseDao {
    /**
     * Save long.
     *
     * @param bill       the bill
     * @param connection the connection
     * @return the long
     * @throws DaoException the dao exception
     */
    long save(Bill bill, Connection connection) throws DaoException;
}
