package com.vinnichenko.bdepot.model.service.impl;

import com.vinnichenko.bdepot.exception.DaoException;
import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.exception.TransactionException;
import com.vinnichenko.bdepot.model.dao.BusDao;
import com.vinnichenko.bdepot.model.dao.DaoFactory;
import com.vinnichenko.bdepot.model.dao.TransactionManager;
import com.vinnichenko.bdepot.model.dao.TripDao;
import com.vinnichenko.bdepot.model.dao.impl.BusDaoImpl;
import com.vinnichenko.bdepot.model.dao.impl.TripDaoImpl;
import com.vinnichenko.bdepot.model.entity.Bus;
import com.vinnichenko.bdepot.model.entity.Trip;
import com.vinnichenko.bdepot.model.service.ServiceFactory;
import com.vinnichenko.bdepot.model.service.TripService;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockObjectFactory;
import org.testng.IObjectFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

@PowerMockIgnore({"javax.xml.*", "org.xml.*", "org.w3c.*",
        "com.sun.org.apache.xalan.*", "javax.management.*", "javax.net.ssl.*"})
@PrepareForTest({DaoFactory.class, TransactionManager.class})
public class TripServiceImplTest {

    TripDao tripDao;
    BusDao busDao;
    TripService tripService;
    TransactionManager transactionManager;

    @ObjectFactory
    public IObjectFactory getObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @BeforeMethod
    public void setUp() {
        PowerMockito.mockStatic(DaoFactory.class);
        DaoFactory daoFactory = Mockito.mock(DaoFactory.class);
        Mockito.when(DaoFactory.getInstance()).thenReturn(daoFactory);
        tripDao = Mockito.mock(TripDaoImpl.class);
        Mockito.when(daoFactory.getTripDao()).thenReturn(tripDao);
        busDao = Mockito.mock(BusDaoImpl.class);
        Mockito.when(daoFactory.getBusDao()).thenReturn(busDao);
        tripService = ServiceFactory.getInstance().getTripService();
        PowerMockito.mockStatic(TransactionManager.class);
        transactionManager = Mockito.mock(TransactionManager.class);
        Mockito.when(TransactionManager.getInstance()).thenReturn(transactionManager);
    }

    @Test
    public void startTripTest() {
        try {
            Mockito.when(transactionManager.startTrip(Mockito.any())).thenReturn(true);
            boolean condition = tripService.startTrip(1L, 1L);
            assertTrue(condition);
        } catch (TransactionException | ServiceException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void startTripTestException() throws TransactionException, ServiceException {
        Mockito.when(transactionManager.startTrip(Mockito.any())).thenThrow(TransactionException.class);
        tripService.startTrip(1L, 1L);
    }

    @Test
    public void findByUserIdTest() {
        List<Trip> trips = new ArrayList<>();
        try {
            Mockito.when(tripDao.findByUserId(Mockito.anyLong())).thenReturn(trips);
            List<Trip> actual = tripService.findByUserId(1L);
            List<Trip> expected = trips;
            assertEquals(actual, expected);
        } catch (DaoException | ServiceException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findByUserIdTestException() throws DaoException, ServiceException {
        Mockito.when(tripDao.findByUserId(Mockito.anyLong())).thenThrow(DaoException.class);
        tripService.findByUserId(1L);
    }

    @Test
    public void finishTripTest() {
        Trip trip = new Trip();
        Bus bus = new Bus();
        bus.setRate(BigDecimal.valueOf(40.5));
        try {
            Mockito.when(tripDao.findById(Mockito.anyLong())).thenReturn(Optional.of(trip));
            List<Bus> buses = new ArrayList<>();
            buses.add(bus);
            Mockito.when(busDao.findByUserId(Mockito.anyLong())).thenReturn(buses);
            Mockito.when(transactionManager.finishTrip(Mockito.any())).thenReturn(true);
            boolean condition = tripService.finishTrip("1");
            assertTrue(condition);
        } catch (ServiceException | TransactionException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void finishTripTestException() throws DaoException, TransactionException, ServiceException {
        Trip trip = new Trip();
        Bus bus = new Bus();
        bus.setRate(BigDecimal.valueOf(40.5));
        Mockito.when(tripDao.findById(Mockito.anyLong())).thenReturn(Optional.of(trip));
        List<Bus> buses = new ArrayList<>();
        buses.add(bus);
        Mockito.when(busDao.findByUserId(Mockito.anyLong())).thenReturn(buses);
        Mockito.when(transactionManager.finishTrip(Mockito.any())).thenThrow(TransactionException.class);
        tripService.finishTrip("1");
    }
}