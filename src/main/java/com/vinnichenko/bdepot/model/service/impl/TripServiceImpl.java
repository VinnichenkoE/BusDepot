package com.vinnichenko.bdepot.model.service.impl;

import com.vinnichenko.bdepot.exception.DaoException;
import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.exception.TransactionException;
import com.vinnichenko.bdepot.model.dao.*;
import com.vinnichenko.bdepot.model.entity.Bus;
import com.vinnichenko.bdepot.model.entity.Trip;
import com.vinnichenko.bdepot.model.service.TripService;
import com.vinnichenko.bdepot.util.DateUtil;
import com.vinnichenko.bdepot.validator.DataValidator;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class TripServiceImpl implements TripService {
    public boolean startTrip(long userId, long orderId) throws ServiceException {
        TransactionManager transactionManager = TransactionManager.getInstance();
        long startDate = Instant.now().toEpochMilli();
        Trip trip = new Trip(startDate, 0L, BigDecimal.ZERO, orderId, userId);
        boolean result;
        try {
            result = transactionManager.startTrip(trip);
        } catch (TransactionException e) {
            throw new ServiceException("Start trip error", e);
        }
        return result;
    }

    @Override
    public List<Trip> findByUserId(long userId) throws ServiceException {
        TripDao tripDao = DaoFactory.getInstance().getTripDao();
        List<Trip> trips;
        try {
            trips = tripDao.findByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException("Find by user  id error", e);
        }
        return trips;
    }

    @Override
    public boolean finishTrip(String tripId) throws ServiceException {
        TripDao tripDao = DaoFactory.getInstance().getTripDao();
        BusDao busDao = DaoFactory.getInstance().getBusDao();
        TransactionManager transactionManager = TransactionManager.getInstance();
        Optional<Trip> trip;
        Bus bus;
        boolean result = false;
        try {
            if (DataValidator.isNumber(tripId)) {
                trip = tripDao.findById(Long.parseLong(tripId));
                if (trip.isPresent()) {
                    long userId = trip.get().getUserId();
                    List<Bus> busList = busDao.findByUserId(userId);
                    if (!busList.isEmpty()) {
                        bus = busList.get(0);
                        long endDate = Instant.now().toEpochMilli();
                        trip.get().setEndDate(endDate);
                        int hours = DateUtil.hoursBetween(trip.get().getStartDate(), endDate);
                        BigDecimal cost = bus.getRate().multiply(BigDecimal.valueOf(hours));
                        trip.get().setCost(cost);
                        result = transactionManager.finishTrip(trip.get());
                    }
                }
            }
        } catch (DaoException | TransactionException e) {
            throw new ServiceException("Finish trip error", e);
        }
        return result;
    }
}
