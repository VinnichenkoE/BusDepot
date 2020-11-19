package com.vinnichenko.bdepot.model.dao;

import com.vinnichenko.bdepot.exception.DaoException;
import com.vinnichenko.bdepot.model.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * The interface User dao.
 * Extends the interface of the {@code BaseDao}
 * Includes methods for the application to interact with User objects in the database.
 */
public interface UserDao extends BaseDao {
    /**
     * Find password by user login.
     *
     * @param login the login
     * @return the string
     * @throws DaoException the dao exception
     */
    String findPasswordByLogin(String login) throws DaoException;

    /**
     * Find user by login.
     *
     * @param login the login
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findUserByLogin(String login) throws DaoException;

    /**
     * Save user and return id from the database.
     *
     * @param user         the user
     * @param hashPassword the hash password
     * @return the long
     * @throws DaoException the dao exception
     */
    long saveUser(User user, String hashPassword) throws DaoException;

    /**
     * Find free drivers.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<User> findFreeDrivers() throws DaoException;

    /**
     * Update user in database.
     *
     * @param user the user
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateUser(User user) throws DaoException;

    /**
     * Update password in the database.
     *
     * @param login    the login
     * @param password the password
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updatePassword(String login, String password) throws DaoException;

    /**
     * Find all.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<User> findAll() throws DaoException;
}
