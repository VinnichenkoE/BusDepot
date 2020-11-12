package com.vinnichenko.bdepot.model.dao;

import com.vinnichenko.bdepot.exception.DaoException;
import com.vinnichenko.bdepot.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends BaseDao {
    String findPasswordByLogin(String login) throws DaoException;

    Optional<User> findUserByLogin(String login) throws DaoException;

    long saveUser(User user, String hashPassword) throws DaoException;

    List<User> findFreeDrivers() throws DaoException;

    boolean updateUser(User user) throws DaoException;

    boolean updatePassword(String login, String password) throws DaoException;

    List<User> findAll() throws DaoException;
}
