package com.vinnichenko.bdepot.model.dao;

import com.vinnichenko.bdepot.exception.DaoException;
import com.vinnichenko.bdepot.model.entity.Bill;

public interface BillDao extends BaseDao {
    long save(Bill bill) throws DaoException;
}
