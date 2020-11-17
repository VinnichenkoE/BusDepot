package com.vinnichenko.bdepot.model.dao;

import com.vinnichenko.bdepot.exception.DaoException;
import com.vinnichenko.bdepot.model.entity.Bill;

import java.sql.Connection;

public interface BillDao extends BaseDao {
    long save(Bill bill, Connection connection) throws DaoException;
}
