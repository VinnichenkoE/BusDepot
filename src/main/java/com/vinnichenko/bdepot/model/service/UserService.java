package com.vinnichenko.bdepot.model.service;

import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface User service.
 * Indicates methods for processing information related to users.
 */
public interface UserService {
    /**
     * Authorizes a user in the system, the result of authorization
     * is represented by a optional of user type.
     *
     * @param parameters the parameters
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> authorize(Map<String, String> parameters) throws ServiceException;

    /**
     * Find user by login.
     *
     * @param login the login
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> findUserByLogin(String login) throws ServiceException;

    /**
     * Save user.
     * Adds a new user to the system.
     * Result of saving is a id of user in the database.
     *
     * @param parameters the parameters
     * @return the long
     * @throws ServiceException the service exception
     */
    long saveUser(Map<String, String> parameters) throws ServiceException;

    /**
     * Find free drivers.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findFreeDrivers() throws ServiceException;

    /**
     * Update user boolean.
     *
     * @param parameters the parameters
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateUser(Map<String, String> parameters) throws ServiceException;

    /**
     * Update user.
     *
     * @param user the user
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateUser(User user) throws ServiceException;

    /**
     * Update password.
     *
     * @param parameters the parameters
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updatePassword(Map<String, String> parameters) throws ServiceException;

    /**
     * Find all.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findAll() throws ServiceException;
}
