package com.vinnichenko.bdepot.model.service;

import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.model.entity.Bus;
import com.vinnichenko.bdepot.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BusService {
    int save(Map<String, String> parameters) throws ServiceException;
    List<Bus> findFreeBusesByNumberSeats(int numberSeats, long startDate, long endDate) throws ServiceException;
    List<Bus> findByUserId(long userId) throws ServiceException;
    List<Bus> findAll() throws ServiceException;
    Map<Bus, User> findAllWithDriver() throws ServiceException;
    Optional<Bus> findById(int id) throws ServiceException;
    boolean appointUser(int vehicleId, long userId) throws ServiceException;
    boolean update(Bus bus) throws ServiceException;
}
