package com.vinnichenko.bdepot.model.service.impl;

import com.vinnichenko.bdepot.exception.DaoException;
import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.exception.UtilException;
import com.vinnichenko.bdepot.model.creator.UserCreator;
import com.vinnichenko.bdepot.model.dao.DaoFactory;
import com.vinnichenko.bdepot.model.dao.UserDao;
import com.vinnichenko.bdepot.model.entity.User;
import com.vinnichenko.bdepot.model.service.UserService;
import com.vinnichenko.bdepot.util.PasswordEncoder;
import com.vinnichenko.bdepot.validator.DataValidator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.vinnichenko.bdepot.model.ParameterKey.*;
import static com.vinnichenko.bdepot.model.ErrorMessageType.*;

/**
 * The type User service.
 */
public class UserServiceImpl implements UserService {
    @Override
    public Optional<User> authorize(Map<String, String> parameters) throws ServiceException {
        UserDao dao = DaoFactory.getInstance().getUserDao();
        Optional<User> result = Optional.empty();
        String login = parameters.get(LOGIN);
        String password = parameters.get(PASSWORD);
        try {
            Optional<User> user = dao.findUserByLogin(login);
            if (user.isPresent() && user.get().getIsActive() == 1) {
                String storedPassword = dao.findPasswordByLogin(login);
                if (PasswordEncoder.check(password, storedPassword)) {
                    result = user;
                } else {
                    parameters.put(MESSAGE, PASSWORD_ERROR);
                }
            } else {
                parameters.put(MESSAGE, LOGIN_ERROR);
            }
        } catch (DaoException | UtilException e) {
            throw new ServiceException("Error during authorization", e);
        }
        return result;
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        UserDao dao = factory.getUserDao();
        try {
            return dao.findUserByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException("Error occurred while searching for a user by login", e);
        }
    }

    @Override
    public long saveUser(Map<String, String> parameters) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDao userDao = daoFactory.getUserDao();
        long id = -1;
        User user;
        try {
            boolean isLoginExist = findUserByLogin(parameters.get(USER_LOGIN)).isPresent();
            if (DataValidator.checkUserData(parameters) && !isLoginExist) {
                user = UserCreator.createUser(parameters);
                id = userDao.saveUser(user, PasswordEncoder.getSaltedHash(parameters.get(PASSWORD)));
                if (id > 0) {
                    parameters.put(USER_ID, String.valueOf(id));
                }
            } else {
                if (isLoginExist) {
                    parameters.put(MESSAGE, LOGIN_ERROR);
                }
            }
        } catch (UtilException | DaoException e) {
            throw new ServiceException("User save error", e);
        }
        return id;
    }

    @Override
    public List<User> findFreeDrivers() throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDao userDao = daoFactory.getUserDao();
        List<User> users;
        try {
            users = userDao.findFreeDrivers();
        } catch (DaoException e) {
            throw new ServiceException("Find driver error", e);
        }
        return users;
    }

    @Override
    public boolean updateUser(Map<String, String> parameters) throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        User user;
        boolean result = false;
        String newLogin = parameters.get(NEW_LOGIN);
        try {
            if (!parameters.get(USER_LOGIN).equals(newLogin)) {

                if (userDao.findUserByLogin(newLogin).isEmpty()) {
                    parameters.put(USER_LOGIN, newLogin);
                } else {
                    parameters.put(USER_LOGIN, EMPTY);
                    parameters.put(MESSAGE, LOGIN_ERROR);
                }
            }
            if (DataValidator.checkUserData(parameters)) {
                user = UserCreator.createUser(parameters);
                result = userDao.updateUser(user);
            }
        } catch (DaoException e) {
            throw new ServiceException("Update users error", e);
        }

        return result;
    }

    @Override
    public boolean updateUser(User user) throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        boolean result;
        try {
            result = userDao.updateUser(user);
        } catch (DaoException e) {
            throw new ServiceException("Update user error", e);
        }
        return result;
    }

    @Override
    public boolean updatePassword(Map<String, String> parameters) throws ServiceException {
        boolean result = false;
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        try {
            if (DataValidator.checkPassword(parameters.get(USER_NEW_PASSWORD))) {
                String login = parameters.get(USER_LOGIN);
                String savedPassword = userDao.findPasswordByLogin(login);
                if (PasswordEncoder.check(parameters.get(USER_OLD_PASSWORD), savedPassword)) {
                    String newHashPassword = PasswordEncoder.getSaltedHash(parameters.get(USER_NEW_PASSWORD));
                    result = userDao.updatePassword(login, newHashPassword);
                } else {
                    parameters.put(MESSAGE, PASSWORD_ERROR);
                }
            } else {
                parameters.put(MESSAGE, NEW_PASSWORD);
            }
        } catch (DaoException | UtilException e) {
            throw new ServiceException("Update password error", e);
        }
        return result;
    }

    @Override
    public List<User> findAll() throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDao userDao = daoFactory.getUserDao();
        List<User> users;
        try {
            users = userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Find all error", e);
        }
        return users;
    }
}
