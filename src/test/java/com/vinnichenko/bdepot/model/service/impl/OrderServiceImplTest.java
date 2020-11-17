package com.vinnichenko.bdepot.model.service.impl;

import com.vinnichenko.bdepot.exception.DaoException;
import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.exception.TransactionException;
import com.vinnichenko.bdepot.model.dao.DaoFactory;
import com.vinnichenko.bdepot.model.dao.OrderDao;
import com.vinnichenko.bdepot.model.dao.TransactionManager;
import com.vinnichenko.bdepot.model.dao.impl.OrderDaoImpl;
import com.vinnichenko.bdepot.model.entity.Order;
import com.vinnichenko.bdepot.model.service.OrderService;
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

import static org.testng.Assert.*;
import static com.vinnichenko.bdepot.model.ParameterKey.*;

@PowerMockIgnore({"javax.xml.*", "org.xml.*", "org.w3c.*",
        "com.sun.org.apache.xalan.*", "javax.management.*", "javax.net.ssl.*"})
@PrepareForTest({DaoFactory.class, TransactionManager.class})
public class OrderServiceImplTest {
    OrderDao orderDao;
    OrderService service;

    @ObjectFactory
    public IObjectFactory getObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @BeforeMethod
    public void setUp() {
        PowerMockito.mockStatic(DaoFactory.class);
        DaoFactory daoFactory = Mockito.mock(DaoFactory.class);
        Mockito.when(DaoFactory.getInstance()).thenReturn(daoFactory);
        orderDao = Mockito.mock(OrderDaoImpl.class);
        Mockito.when(daoFactory.getOrderDao()).thenReturn(orderDao);
        service = ServiceFactory.getInstance().getOrderService();
    }

    @Test
    public void findSubmittedOrdersTest() {
        try {
            Mockito.when(orderDao.findSubmittedOrders()).thenReturn(new ArrayList<Order>());
            List<Order> actual = service.findSubmittedOrders();
            List<Order> expected = new ArrayList<>();
            assertEquals(actual, expected);
        } catch (DaoException | ServiceException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findSubmittedOrdersTestException() throws DaoException, ServiceException {
        Mockito.when(orderDao.findSubmittedOrders()).thenThrow(DaoException.class);
        service.findSubmittedOrders();
    }

    @Test
    public void findByIdTest() {
        try {
            Order order = new Order();
            Mockito.when(orderDao.findOrderById(Mockito.anyLong())).thenReturn(Optional.of(order));
            Optional<Order> actual = service.findById("1");
            Optional<Order> expected = Optional.of(order);
            assertEquals(actual, expected);
        } catch (DaoException | ServiceException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findByIdTestException() throws DaoException, ServiceException {
        Mockito.when(orderDao.findOrderById(Mockito.anyLong())).thenThrow(DaoException.class);
        service.findById("1");
    }

    @DataProvider(name = "orderData")
    public Object[][] createOrderData() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(NUMBER_OF_SEATS, "16");
        parameters.put(START_DATE, "2021-11-16T10:00");
        parameters.put(END_DATE, "2021-11-16T16:00");
        parameters.put(START_POINT, "Minsk");
        parameters.put(END_POINT, "Polotsk");
        parameters.put(DISTANCE, "275");
        parameters.put(USER_ID, "1");
        return new Object[][] {{parameters}};
    }

    @Test(dataProvider = "orderData")
    public void saveTest(Map<String, String> parameters) {
        try {
            PowerMockito.mockStatic(TransactionManager.class);
            TransactionManager transactionManager = Mockito.mock(TransactionManager.class);
            Mockito.when(TransactionManager.getInstance()).thenReturn(transactionManager);
            Mockito.when(transactionManager.addOrder(Mockito.any(), Mockito.anyLong())).thenReturn(1L);
            long actual = service.save(parameters);
            long expected = 1L;
            assertEquals(actual, expected);
        } catch (ServiceException | TransactionException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class, dataProvider = "orderData")
    public void saveTestException(Map<String, String> parameters) throws ServiceException, TransactionException {
        PowerMockito.mockStatic(TransactionManager.class);
        TransactionManager transactionManager = Mockito.mock(TransactionManager.class);
        Mockito.when(TransactionManager.getInstance()).thenReturn(transactionManager);
        Mockito.when(transactionManager.addOrder(Mockito.any(), Mockito.anyLong())).thenThrow(TransactionException.class);
        service.save(parameters);
    }

    @Test
    public void findUserOrdersTest() {
        try {
            List<Order> orders = new ArrayList<>();
            Mockito.when(orderDao.findUserOrders(Mockito.anyLong())).thenReturn(orders);
            List<Order> actual = service.findUserOrders(1L);
            List<Order> expected = orders;
            assertEquals(actual, expected);
        } catch (DaoException | ServiceException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findUserOrdersTestException() throws DaoException, ServiceException {
        Mockito.when(orderDao.findUserOrders(Mockito.anyLong())).thenThrow(DaoException.class);
        service.findUserOrders(1L);
    }

    @Test
    public void appointDriverTest() {
        try {
            PowerMockito.mockStatic(TransactionManager.class);
            TransactionManager transactionManager = Mockito.mock(TransactionManager.class);
            Mockito.when(TransactionManager.getInstance()).thenReturn(transactionManager);
            Mockito.when(transactionManager.appointDriver(Mockito.anyLong(), Mockito.anyLong())).thenReturn(true);
            boolean condition = service.appointDriver("1", "1");
            assertTrue(condition);
        } catch (ServiceException | TransactionException e) {
            fail(e.getMessage());
        }
    }
}