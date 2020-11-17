package com.vinnichenko.bdepot.model.service.impl;

import com.vinnichenko.bdepot.exception.DaoException;
import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.exception.UtilException;
import com.vinnichenko.bdepot.model.creator.UserCreator;
import com.vinnichenko.bdepot.model.dao.DaoFactory;
import com.vinnichenko.bdepot.model.dao.UserDao;
import com.vinnichenko.bdepot.model.dao.impl.UserDaoImpl;
import com.vinnichenko.bdepot.model.entity.User;
import com.vinnichenko.bdepot.model.service.ServiceFactory;
import com.vinnichenko.bdepot.model.service.UserService;
import com.vinnichenko.bdepot.util.PasswordEncoder;
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
@PrepareForTest({DaoFactory.class, PasswordEncoder.class, UserCreator.class})
public class UserServiceImplTest {

    UserDao userDao;
    UserService service;

    @ObjectFactory
    public IObjectFactory getObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @BeforeMethod
    public void setUp() {
        PowerMockito.mockStatic(DaoFactory.class);
        DaoFactory daoFactory = Mockito.mock(DaoFactory.class);
        Mockito.when(DaoFactory.getInstance()).thenReturn(daoFactory);
        userDao = Mockito.mock(UserDaoImpl.class);
        Mockito.when(daoFactory.getUserDao()).thenReturn(userDao);
        service = ServiceFactory.getInstance().getUserService();
    }

    @DataProvider(name = "userData")
    public Object[][] createUserData() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(LOGIN, "user");
        parameters.put(PASSWORD, "password");
        parameters.put(USER_NAME, "Ivan");
        parameters.put(USER_SURNAME, "Ivanov");
        parameters.put(USER_PHONE_NUMBER, "375298556545");
        parameters.put(USER_NEW_PASSWORD, "1111");
        parameters.put(USER_OLD_PASSWORD, "1234");
        return new Object[][]{{parameters}};
    }

    @Test(dataProvider = "userData")
    public void authorizeTest(Map<String, String> parameters) {
        try {
            PowerMockito.mockStatic(PasswordEncoder.class);
            User user = new User();
            user.setIsActive((byte) 1);
            Mockito.when(userDao.findUserByLogin(Mockito.anyString())).thenReturn(Optional.of(user));
            Mockito.when(userDao.findPasswordByLogin(Mockito.anyString())).thenReturn("1234");
            Mockito.when(PasswordEncoder.check(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
            Optional<User> actual = service.authorize(parameters);
            Optional<User> expected = Optional.of(user);
            assertEquals(actual, expected);
        } catch (DaoException | ServiceException | UtilException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class, dataProvider = "userData")
    public void authorizeTestException(Map<String, String> parameters) throws DaoException, ServiceException {
        Mockito.when(userDao.findUserByLogin(Mockito.anyString())).thenThrow(DaoException.class);
        service.authorize(parameters);
    }

    @Test
    public void findUserByLoginTest() {
        try {
            Mockito.when(userDao.findUserByLogin(Mockito.anyString())).thenReturn(Optional.empty());
            Optional<User> actual = service.findUserByLogin("user");
            Optional<User> expected = Optional.empty();
            assertEquals(actual, expected);
        } catch (DaoException | ServiceException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findByLoginTestException() throws DaoException, ServiceException {
        Mockito.when(userDao.findUserByLogin(Mockito.anyString())).thenThrow(DaoException.class);
        service.findUserByLogin("user");
    }

    @Test(dataProvider = "userData")
    public void saveUserTest(Map<String, String> parameters) {
        try {
            PowerMockito.mockStatic(UserCreator.class);
            PowerMockito.mockStatic(PasswordEncoder.class);
            Mockito.when(userDao.saveUser(Mockito.any(), Mockito.anyString())).thenReturn(1L);
            Mockito.when(UserCreator.createUser(Mockito.anyMap())).thenReturn(new User());
            Mockito.when(PasswordEncoder.getSaltedHash(Mockito.anyString())).thenReturn("1234");
            long actual = service.saveUser(parameters);
            long expected = 1L;
            assertEquals(actual, expected);
        } catch (DaoException | ServiceException | UtilException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class, dataProvider = "userData")
    public void saveUserTestException(Map<String, String> parameters) throws DaoException, ServiceException, UtilException {
        PowerMockito.mockStatic(UserCreator.class);
        PowerMockito.mockStatic(PasswordEncoder.class);
        Mockito.when(userDao.saveUser(Mockito.any(), Mockito.anyString())).thenThrow(DaoException.class);
        Mockito.when(UserCreator.createUser(Mockito.anyMap())).thenReturn(new User());
        Mockito.when(PasswordEncoder.getSaltedHash(Mockito.anyString())).thenReturn("1234");
        service.saveUser(parameters);
    }

    @Test
    public void findFreeDriversTest() {
        try {
            Mockito.when(userDao.findFreeDrivers()).thenReturn(new ArrayList<User>());
            List<User> actual = service.findFreeDrivers();
            List<User> expected = new ArrayList<>();
            assertEquals(actual, expected);
        } catch (DaoException | ServiceException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findFreeDriversTestException() throws DaoException, ServiceException {
        Mockito.when(userDao.findFreeDrivers()).thenThrow(DaoException.class);
        service.findFreeDrivers();
    }

    @Test
    public void updateUserTest() {
        try {
            Mockito.when(userDao.updateUser(Mockito.any())).thenReturn(true);
            User user = new User();
            boolean condition = service.updateUser(user);
            assertTrue(condition);
        } catch (DaoException | ServiceException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void updateUserException() throws DaoException, ServiceException {
        Mockito.when(userDao.updateUser(Mockito.any())).thenThrow(DaoException.class);
        User user = new User();
        service.updateUser(user);
    }

    @Test(dataProvider = "userData")
    public void updatePasswordTest(Map<String, String> parameters) {
        try {
            PowerMockito.mockStatic(PasswordEncoder.class);
            Mockito.when(PasswordEncoder.check(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
            Mockito.when(userDao.findPasswordByLogin(Mockito.anyString())).thenReturn("1234");
            Mockito.when(userDao.updatePassword(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
            Mockito.when(PasswordEncoder.getSaltedHash(Mockito.anyString())).thenReturn("1234");
            boolean condition = service.updatePassword(parameters);
            assertTrue(condition);
        } catch (DaoException | ServiceException | UtilException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void findAllTest() {
        try {
            Mockito.when(userDao.findAll()).thenReturn(new ArrayList<User>());
            List<User> actual = service.findAll();
            List<User> expected = new ArrayList<>();
            assertEquals(actual, expected);
        } catch (DaoException | ServiceException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findAllTestException() throws DaoException, ServiceException {
        Mockito.when(userDao.findAll()).thenThrow(DaoException.class);
        service.findAll();
    }
}