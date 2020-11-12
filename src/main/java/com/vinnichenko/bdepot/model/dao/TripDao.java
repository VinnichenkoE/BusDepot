package com.vinnichenko.bdepot.model.dao;

import com.vinnichenko.bdepot.exception.DaoException;
import com.vinnichenko.bdepot.model.entity.Trip;

import java.util.List;
import java.util.Optional;

public interface TripDao extends BaseDao {
    long save(Trip trip) throws DaoException;
    List<Trip> findByUserId(long userId) throws DaoException;
    Optional<Trip> findById(long tripId) throws DaoException;
    boolean update(Trip trip) throws DaoException;
}
