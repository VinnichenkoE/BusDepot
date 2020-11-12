package com.vinnichenko.bdepot.model.service.impl;

import com.vinnichenko.bdepot.exception.DaoException;
import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.model.creator.BusCreator;
import com.vinnichenko.bdepot.model.dao.BusDao;
import com.vinnichenko.bdepot.model.dao.DaoFactory;
import com.vinnichenko.bdepot.model.entity.Bus;
import com.vinnichenko.bdepot.model.entity.User;
import com.vinnichenko.bdepot.model.service.BusService;
import com.vinnichenko.bdepot.validator.DataValidator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BusServiceImpl implements BusService {
    @Override
    public int save(Map<String, String> parameters) throws ServiceException {
        int id = -1;
        Bus bus;
        BusDao dao = DaoFactory.getInstance().getBusDao();
        if (DataValidator.checkBusData(parameters)) {
            bus = BusCreator.createBus(parameters);
            try {
                id = dao.save(bus);
            } catch (DaoException e) {
                throw new ServiceException("Save bus exception", e);
            }
        }
        return id;
    }

    @Override
    public List<Bus> findFreeBusesByNumberSeats(int numberSeats, long startDate, long endDate) throws ServiceException {
        BusDao dao = DaoFactory.getInstance().getBusDao();
        List<Bus> buses;
        try {
            buses = dao.findFreeBusesByNumberSeats(numberSeats, startDate, endDate);
        } catch (DaoException e) {
            throw new ServiceException("Find free buses by number of seats error", e);
        }
        return buses;
    }

    @Override
    public List<Bus> findByUserId(long userId) throws ServiceException {
        BusDao dao = DaoFactory.getInstance().getBusDao();
        List<Bus> buses;
        try {
            buses = dao.findByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException("Find by users id error", e);
        }
        return buses;
    }

    @Override
    public List<Bus> findAll() throws ServiceException {
        BusDao dao = DaoFactory.getInstance().getBusDao();
        List<Bus> buses;
        try {
            buses = dao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Find all error", e);
        }
        return buses;
    }

    @Override
    public Map<Bus, User> findAllWithDriver() throws ServiceException {
        BusDao dao = DaoFactory.getInstance().getBusDao();
        Map<Bus, User> result;
        try {
            result = dao.findAllWithDrivers();
        } catch (DaoException e) {
            throw new ServiceException("Find all with driver error", e);
        }
        return result;
    }

    @Override
    public Optional<Bus> findById(int id) throws ServiceException {
        BusDao dao = DaoFactory.getInstance().getBusDao();
        Optional<Bus> result;
        try {
            result = dao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("Find by id error", e);
        }
        return result;
    }

    @Override
    public boolean appointUser(int busId, long userId) throws ServiceException {
        BusDao dao = DaoFactory.getInstance().getBusDao();
        boolean result;
        try {
            Optional<Bus> bus = findById(busId);
            if (bus.isPresent()) {
                bus.get().setUserId(userId);
                bus.get().setStatus(Bus.BusStatus.READY);
                result = dao.update(bus.get());
            } else {
                result = false;
            }
        } catch (DaoException e) {
            throw new ServiceException("Appoint user error", e);
        }
        return result;
    }

    @Override
    public boolean update(Bus bus) throws ServiceException {
        BusDao dao = DaoFactory.getInstance().getBusDao();
        try {
            return dao.update(bus);
        } catch (DaoException e) {
            throw new ServiceException("Update bus error", e);
        }
    }
}
