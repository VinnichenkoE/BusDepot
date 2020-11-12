package com.vinnichenko.bdepot.model.dao;

import com.vinnichenko.bdepot.exception.DaoException;
import com.vinnichenko.bdepot.model.entity.Bus;
import com.vinnichenko.bdepot.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BusDao extends BaseDao {
    int save(Bus bus) throws DaoException;

    List<Bus> findFreeBusesByNumberSeats(int numberOfSeats, long startDate, long endDate) throws DaoException;

    List<Bus> findByUserId(long userId) throws DaoException;

    List<Bus> findAll() throws DaoException;

    Map<Bus, User> findAllWithDrivers() throws DaoException;

    Optional<Bus> findById(int id) throws DaoException;

    boolean update(Bus bus) throws DaoException;

    boolean updateBusStatus(long userId, Bus.BusStatus status) throws DaoException;
}
