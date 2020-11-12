package com.vinnichenko.bdepot.model.service.impl;

import com.vinnichenko.bdepot.exception.DaoException;
import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.model.dao.*;
import com.vinnichenko.bdepot.model.entity.Bill;
import com.vinnichenko.bdepot.model.entity.Bus;
import com.vinnichenko.bdepot.model.entity.Order;
import com.vinnichenko.bdepot.model.entity.Trip;
import com.vinnichenko.bdepot.model.service.TripService;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class TripServiceImpl implements TripService {
    public boolean startTrip(long userId, long orderId) throws ServiceException {
        TripDao tripDao = DaoFactory.getInstance().getTripDao();
        OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
        long startDate = Instant.now().toEpochMilli();
        Trip trip = new Trip(startDate, 0L, BigDecimal.ZERO, orderId, userId);
        boolean result = false;
        try {
            if (tripDao.save(trip) > 0) {
                result = orderDao.updateOrderStatus(orderId, Order.OrderStatus.IN_PROCESS);
            }
        } catch (DaoException e) {
            throw new ServiceException("start trip error", e);
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
            throw new ServiceException("", e);
        }
        return trips;
    }

    @Override
    public boolean finishTrip(long tripId) throws ServiceException {
        TripDao tripDao = DaoFactory.getInstance().getTripDao();
        BusDao busDao = DaoFactory.getInstance().getBusDao();
        OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
        BillDao billDao = DaoFactory.getInstance().getBillDao();
        Optional<Trip> trip;
        Bus bus;
        boolean result = false;
        try {
            trip = tripDao.findById(tripId);
            if (trip.isPresent()) {
                long userId = trip.get().getUserId();
                bus = busDao.findByUserId(userId).get(0);
                trip.get().setEndDate(Instant.now().toEpochMilli());
                long hours = (trip.get().getEndDate() - trip.get().getStartDate()) / (1000 * 60 * 60);
                BigDecimal cost = bus.getRate().multiply(BigDecimal.valueOf(hours));
                trip.get().setCost(cost);
                if (tripDao.update(trip.get())) {
                    orderDao.updateOrderStatus(trip.get().getOrderId(), Order.OrderStatus.COMPLETED);
                    long customerId = orderDao.findCustomerId(trip.get().getOrderId());
                    Bill bill = new Bill(cost, (byte) 0, trip.get().getOrderId(), customerId);
                    result = billDao.save(bill) > 0;
                }
            }
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
        return result;
    }
}
