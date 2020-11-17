package com.vinnichenko.bdepot.model.dao.impl;

import com.vinnichenko.bdepot.exception.DaoException;
import com.vinnichenko.bdepot.model.dao.BusDao;
import com.vinnichenko.bdepot.model.entity.Bus;
import com.vinnichenko.bdepot.model.entity.User;
import com.vinnichenko.bdepot.model.pool.ConnectionPool;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

import static com.vinnichenko.bdepot.model.dao.ColumnLabel.*;

public class BusDaoImpl implements BusDao {

    private static final String SQL_SAVE = "INSERT INTO buses (brand, model, registration_number, number_of_seats, " +
            "rate, image_name, bus_status_id_fk) VALUES (?, ?, ?, ?, ?, ?, 2);";
    private static final String SQL_FIND_FREE_VEHICLES_BY_NUMBER_SEATS = "SELECT bus_id, brand, model, " +
            "registration_number, number_of_seats, rate, image_name, bus_status_id_fk, user_id_fk FROM buses " +
            "WHERE number_of_seats >= ? AND bus_status_id_fk = 0 AND user_id_fk > 0 AND user_id_fk NOT IN " +
            "(SELECT user_id_fk FROM user_orders uo JOIN orders o ON uo.order_id_fk = o.order_id WHERE " +
            "(? BETWEEN o.start_date and o.end_date) OR (? between o.start_date and o.end_date) " +
            "OR (? <= o.start_date AND ? >= o.end_date));";
    private static final String SQL_FIND_BY_USER_ID = "SELECT bus_id, brand, model, registration_number, " +
            "number_of_seats, rate, image_name, bus_status_id_fk, user_id_fk FROM buses WHERE user_id_fk = ?;";
    private static final String SQL_FIND_ALL = "SELECT bus_id, brand, model, registration_number, number_of_seats, " +
            "rate, image_name, bus_status_id_fk, user_id_fk FROM buses;";
    private static final String SQL_FIND_ALL_WITH_DRIVER = "SELECT b.bus_id, b.brand, b.model, b.registration_number, " +
            "b.number_of_seats, b.rate, b.image_name, b.bus_status_id_fk, b.user_id_fk, u.login, u.is_active, " +
            "u.name, u.surname, u.phone_number, u.role_id_fk FROM buses b LEFT JOIN users u ON b.user_id_fk = u.user_id;";
    private static final String SQL_FIND_BY_ID = "SELECT bus_id, brand, model, registration_number, number_of_seats, " +
            "rate, image_name, bus_status_id_fk, user_id_fk FROM buses WHERE bus_id = ?;";
    private static final String SQL_UPDATE = "UPDATE buses SET brand = ?, model = ?, registration_number = ?, " +
            "number_of_seats = ?, rate= ?, image_name = ?, bus_status_id_fk = ?, user_id_fk = ? WHERE bus_id = ?;";
    private static final String SQL_UPDATE_STATUS = "UPDATE buses SET bus_status_id_fk = ? WHERE user_id_fk = ?;";
    private ConnectionPool pool = ConnectionPool.INSTANCE;

    @Override
    public int save(Bus bus) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int id = -1;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, bus.getBrand());
            statement.setString(2, bus.getModel());
            statement.setString(3, bus.getRegistrationNumber());
            statement.setInt(4, bus.getNumberOfSeats());
            statement.setBigDecimal(5, bus.getRate());
            statement.setString(6, bus.getImageName());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while saving the bus: = " + bus, e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return id;
    }

    @Override
    public List<Bus> findFreeBusesByNumberSeats(int numberOfSeats, long startDate, long endDate) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Bus> buses = new ArrayList<>();
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_FREE_VEHICLES_BY_NUMBER_SEATS);
            preparedStatement.setInt(1, numberOfSeats);
            preparedStatement.setLong(2, startDate);
            preparedStatement.setLong(3, endDate);
            preparedStatement.setLong(4, startDate);
            preparedStatement.setLong(5, endDate);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Bus bus = busFromResultSet(resultSet);
                buses.add(bus);
            }
        } catch (SQLException e) {
            throw new DaoException("Find free buses error", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(preparedStatement);
            pool.releaseConnection(connection);
        }
        return buses;
    }

    @Override
    public List<Bus> findByUserId(long userId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Bus bus;
        List<Bus> buses = new ArrayList<>();
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_USER_ID);
            preparedStatement.setLong(1, userId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                bus = busFromResultSet(resultSet);
                buses.add(bus);
            }
        } catch (SQLException e) {
            throw new DaoException("Find bus by user id error", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(preparedStatement);
            pool.releaseConnection(connection);
        }
        return buses;
    }

    @Override
    public List<Bus> findAll() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Bus bus;
        List<Bus> vehicles = new ArrayList<>();
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bus = busFromResultSet(resultSet);
                vehicles.add(bus);
            }
        } catch (SQLException e) {
            throw new DaoException("Find all error", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(preparedStatement);
            pool.releaseConnection(connection);
        }
        return vehicles;
    }

    @Override
    public Map<Bus, User> findAllWithDrivers() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Bus bus;
        User user;
        Map<Bus, User> result = new HashMap<>();
        try {
            connection = pool.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_FIND_ALL_WITH_DRIVER);
            while (resultSet.next()) {
                bus = busFromResultSet(resultSet);
                String login = resultSet.getString(LOGIN);
                byte isActive = resultSet.getByte(IS_ACTIVE);
                String name = resultSet.getString(NAME);
                String surname = resultSet.getString(SURNAME);
                String phoneNumber = resultSet.getString(PHONE_NUMBER);
                int roleId = resultSet.getInt(USERS_ROLE_ID);
                user = new User(login, isActive, name, surname, phoneNumber, User.Role.values()[roleId]);
                result.put(bus, user);
            }
        } catch (SQLException e) {
            throw new DaoException("Find all with drivers error", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return result;
    }

    @Override
    public Optional<Bus> findById(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Bus bus = null;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                bus = busFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Find by id error", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(preparedStatement);
            pool.releaseConnection(connection);
        }
        return Optional.ofNullable(bus);
    }

    @Override
    public boolean update(Bus bus) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean result;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, bus.getBrand());
            preparedStatement.setString(2, bus.getModel());
            preparedStatement.setString(3, bus.getRegistrationNumber());
            preparedStatement.setInt(4, bus.getNumberOfSeats());
            preparedStatement.setBigDecimal(5, bus.getRate());
            preparedStatement.setString(6, bus.getImageName());
            if (bus.getUserId() == 0) {
                preparedStatement.setInt(7, Bus.BusStatus.NOT_ASSIGNED.ordinal());
                preparedStatement.setNull(8, Types.INTEGER);
            } else {
                preparedStatement.setInt(7, bus.getStatus().ordinal());
                preparedStatement.setLong(8, bus.getUserId());
            }
            preparedStatement.setLong(9, bus.getBusId());
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Update bus error", e);
        } finally {
            closeStatement(preparedStatement);
            pool.releaseConnection(connection);
        }
        return result;
    }

    @Override
    public boolean updateBusStatus(long userId, Bus.BusStatus status) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean result;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_STATUS);
            preparedStatement.setInt(1, status.ordinal());
            preparedStatement.setLong(2, userId);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Update bus status error", e);
        } finally {
            closeStatement(preparedStatement);
            pool.releaseConnection(connection);
        }
        return result;
    }

    private Bus busFromResultSet(ResultSet resultSet) throws SQLException {
        int busId = resultSet.getInt(BUS_ID);
        String brand = resultSet.getString(BRAND);
        String model = resultSet.getString(MODEL);
        String registrationNumber = resultSet.getString(REGISTRATION_NUMBER);
        int numberOfSeats = resultSet.getInt(NUMBER_OF_SEATS);
        BigDecimal rate = resultSet.getBigDecimal(RATE);
        String imageName = resultSet.getString(IMAGE_NAME);
        int statusId = resultSet.getInt(BUSES_BUS_STATUS_ID);
        long userId = resultSet.getLong(BUSES_USER_ID);
        Bus bus = new Bus(busId, brand, model, registrationNumber, numberOfSeats, rate,
                imageName, Bus.BusStatus.values()[statusId], userId);
        return bus;
    }
}
