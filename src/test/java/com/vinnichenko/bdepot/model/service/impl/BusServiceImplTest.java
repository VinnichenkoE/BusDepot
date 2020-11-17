package com.vinnichenko.bdepot.model.service.impl;

import com.vinnichenko.bdepot.exception.DaoException;
import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.model.dao.BusDao;
import com.vinnichenko.bdepot.model.dao.DaoFactory;
import com.vinnichenko.bdepot.model.dao.TransactionManager;
import com.vinnichenko.bdepot.model.dao.impl.BusDaoImpl;
import com.vinnichenko.bdepot.model.entity.Bus;
import com.vinnichenko.bdepot.model.entity.User;
import com.vinnichenko.bdepot.model.service.BusService;
import com.vinnichenko.bdepot.model.service.ServiceFactory;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockObjectFactory;
import org.testng.IObjectFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

import java.util.*;

import static com.vinnichenko.bdepot.model.ParameterKey.*;
import static org.testng.Assert.*;

@PowerMockIgnore({"javax.xml.*", "org.xml.*", "org.w3c.*",
        "com.sun.org.apache.xalan.*", "javax.management.*", "javax.net.ssl.*"})
@PrepareForTest({DaoFactory.class, TransactionManager.class})
public class BusServiceImplTest {
    BusDao busDao;
    BusService busService;

    @ObjectFactory
    public IObjectFactory getObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @BeforeMethod
    public void setUp() {
        PowerMockito.mockStatic(DaoFactory.class);
        DaoFactory daoFactory = Mockito.mock(DaoFactory.class);
        Mockito.when(DaoFactory.getInstance()).thenReturn(daoFactory);
        busDao = Mockito.mock(BusDaoImpl.class);
        Mockito.when(daoFactory.getBusDao()).thenReturn(busDao);
        busService = ServiceFactory.getInstance().getBusService();
    }

    @DataProvider(name = "busData")
    public Object[][] createBusData() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(BRAND, "ГАЗель");
        parameters.put(MODEL, "NEXT");
        parameters.put(NUMBER_OF_SEATS, "16");
        parameters.put(REGISTRATION_NUMBER, "1355 АА-7");
        parameters.put(RATE, "44.5");
        return new Object[][]{{parameters}};

    }

    @Test(dataProvider = "busData")
    public void saveTest(Map<String, String> parameters) {
        try {
            Mockito.when(busDao.save(Mockito.any())).thenReturn(1);
            int actual = busService.save(parameters);
            int expected = 1;
            assertEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(dataProvider = "busData", expectedExceptions = ServiceException.class)
    public void saveTestException(Map<String, String> parameters) throws DaoException, ServiceException {
        Mockito.when(busDao.save(Mockito.any())).thenThrow(DaoException.class);
        busService.save(parameters);
    }

    @Test
    public void findFreeBusesByNumberSeatsTest() {
        try {
            List<Bus> buses = new ArrayList<>();
            Mockito.when(busDao.findFreeBusesByNumberSeats(Mockito.anyInt(), Mockito.anyLong(), Mockito.anyLong()))
                    .thenReturn(buses);
            List<Bus> actual = busService.findFreeBusesByNumberSeats(16, 1L, 3600000L);
            List<Bus> expected = buses;
            assertEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findFreeBusesByNumberSeatsTestException() throws ServiceException, DaoException {
        Mockito.when(busDao.findFreeBusesByNumberSeats(Mockito.anyInt(), Mockito.anyLong(), Mockito.anyLong()))
                .thenThrow(DaoException.class);
        busService.findFreeBusesByNumberSeats(16, 1L, 3600000L);
    }

    @Test
    public void findByUserIdTest() {
        List<Bus> buses = new ArrayList<>();
        try {
            Mockito.when(busDao.findByUserId(Mockito.anyLong())).thenReturn(buses);
            List<Bus> actual = busService.findByUserId(1L);
            List<Bus> expected = buses;
            assertEquals(actual, expected);
        } catch (DaoException | ServiceException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findByUserIdTestException() throws DaoException, ServiceException {
        Mockito.when(busDao.findByUserId(Mockito.anyLong())).thenThrow(DaoException.class);
        busService.findByUserId(1L);
    }

    @Test
    public void findAllTest() {
        List<Bus> buses = new ArrayList<>();
        try {
            Mockito.when(busDao.findAll()).thenReturn(buses);
            List<Bus> actual = busService.findAll();
            List<Bus> expected = buses;
            assertEquals(actual, expected);
        } catch (DaoException | ServiceException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findAllTestException() throws DaoException, ServiceException {
        Mockito.when(busDao.findAll()).thenThrow(DaoException.class);
        busService.findAll();
    }

    @Test
    public void findAllWithDriverTest() {
        Map<Bus, User> buses = new HashMap<>();
        try {
            Mockito.when(busDao.findAllWithDrivers()).thenReturn(buses);
            Map<Bus, User> actual = busService.findAllWithDriver();
            Map<Bus, User> expected = buses;
            assertEquals(actual, expected);
        } catch (DaoException | ServiceException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findAllWithDriversTestException() throws DaoException, ServiceException {
        Mockito.when(busDao.findAllWithDrivers()).thenThrow(DaoException.class);
        busService.findAllWithDriver();
    }

    @Test
    public void findByIdTest() {
        Bus bus = new Bus();
        try {
            Mockito.when(busDao.findById(Mockito.anyInt())).thenReturn(Optional.of(bus));
            Optional<Bus> actual = busService.findById("1");
            Optional<Bus> expected = Optional.of(bus);
            assertEquals(actual, expected);
        } catch (DaoException | ServiceException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findByIdTestException() throws DaoException, ServiceException {
        Mockito.when(busDao.findById(Mockito.anyInt())).thenThrow(DaoException.class);
        busService.findById("1");
    }

    @Test
    public void appointUserTest() {
        Bus bus = new Bus();
        try {
            Mockito.when(busDao.findById(Mockito.anyInt())).thenReturn(Optional.of(bus));
            Mockito.when(busDao.update(Mockito.any())).thenReturn(true);
            boolean condition = busService.appointUser("1", "1");
            assertTrue(condition);
        } catch (DaoException | ServiceException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void updateTest() {
        Bus bus = new Bus();
        try {
            Mockito.when(busDao.update(Mockito.any())).thenReturn(true);
            boolean condition = busService.update(bus);
            assertTrue(condition);
        } catch (DaoException | ServiceException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void updateTestException() throws DaoException, ServiceException {
        Mockito.when(busDao.update(Mockito.any())).thenThrow(DaoException.class);
        busService.update(new Bus());
    }
}