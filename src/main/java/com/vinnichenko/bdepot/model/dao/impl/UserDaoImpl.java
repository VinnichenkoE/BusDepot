package com.vinnichenko.bdepot.model.dao.impl;

import com.vinnichenko.bdepot.exception.DaoException;
import com.vinnichenko.bdepot.model.dao.UserDao;
import com.vinnichenko.bdepot.model.entity.User;
import com.vinnichenko.bdepot.model.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.vinnichenko.bdepot.model.dao.ColumnLabel.*;

public class UserDaoImpl implements UserDao {

    private static final String SQL_FIND_PASSWORD_BY_LOGIN = "SELECT password FROM users WHERE login = ?;";
    private static final String SQL_SELECT_USER = "SELECT user_id, login, is_active, name, surname, phone_number, role_id_fk FROM users WHERE login = ?;";
    private static final String SQL_INSERT_USER = "INSERT INTO users (login, password, is_active, name, surname, phone_number, role_id_fk) values (?, ?, 1, ? , ? , ? , ?);";
    private static final String SQL_FIND_FREE_DRIVERS = "SELECT user_id, login, is_active, name, surname, phone_number, role_id_fk FROM users WHERE role_id_fk = 1 AND is_active = 1 AND user_id NOT IN (SELECT user_id_fk FROM buses where user_id_fk > 0);";
    private static final String SQL_UPDATE_USER = "UPDATE users SET login = ?, is_active = ?, name = ?, surname = ?, phone_number = ? WHERE user_id = ?;";
    private static final String SQL_UPDATE_PASSWORD = "UPDATE users SET password = ? WHERE login = ?;";
    private static final String SQL_FIND_ALL = "SELECT user_id, login, is_active, name, surname, phone_number, role_id_fk FROM users WHERE role_id_fk != 0;";

    private ConnectionPool pool = ConnectionPool.INSTANCE;

    @Override
    public String findPasswordByLogin(String login) throws DaoException {
        String password = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.prepareStatement(SQL_FIND_PASSWORD_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                password = resultSet.getString(PASSWORD);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding password by login:=" + login, e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return password;
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_SELECT_USER);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = userFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding user by login:=" + login, e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public long saveUser(User user, String hashPassword) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        long id = 0;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, hashPassword);
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getSurname());
            preparedStatement.setString(5, user.getPhoneNumber());
            preparedStatement.setInt(6, user.getRole().ordinal());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new DaoException("Save user error", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(preparedStatement);
            pool.releaseConnection(connection);
        }
        return id;
    }

    @Override
    public List<User> findFreeDrivers() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        User user;
        try {
            connection = pool.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_FIND_FREE_DRIVERS);
            while (resultSet.next()) {
                user = userFromResultSet(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding free drivers", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return users;
    }

    @Override
    public boolean updateUser(User user) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean result;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setByte(2, user.getIsActive());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getSurname());
            preparedStatement.setString(5, user.getPhoneNumber());
            preparedStatement.setLong(6, user.getUserId());
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Update user error", e);
        } finally {
            closeStatement(preparedStatement);
            pool.releaseConnection(connection);
        }
        return result;
    }

    @Override
    public boolean updatePassword(String login, String password) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean result;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_PASSWORD);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, login);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Update password error", e);
        } finally {
            closeStatement(preparedStatement);
            pool.releaseConnection(connection);
        }
        return result;
    }

    @Override
    public List<User> findAll() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        User user;
        try {
            connection = pool.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_FIND_ALL);
            while (resultSet.next()) {
                user = userFromResultSet(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding all", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return users;
    }

    private User userFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(USER_ID);
        String login = resultSet.getString(LOGIN);
        byte isActive = resultSet.getByte(IS_ACTIVE);
        String name = resultSet.getString(NAME);
        String surname = resultSet.getString(SURNAME);
        String phoneNumber = resultSet.getString(PHONE_NUMBER);
        int roleId = resultSet.getInt(USERS_ROLE_ID);
        User user = new User(id, login, isActive, name, surname, phoneNumber, User.Role.values()[roleId]);
        return user;
    }
}
