package com.vinnichenko.bdepot.model.service;

import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.model.entity.Trip;

import java.util.List;

public interface TripService {
    boolean startTrip(long userId, long orderId) throws ServiceException;

    List<Trip> findByUserId(long userId) throws ServiceException;

    boolean finishTrip(String tripId) throws ServiceException;
}
