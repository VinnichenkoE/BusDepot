package com.vinnichenko.bdepot.model.dao;

import com.vinnichenko.bdepot.model.dao.impl.*;

/**
 * The type Dao factory.
 */
public final class DaoFactory {
    private static final DaoFactory INSTANCE = new DaoFactory();

    private final BusDao busDao = new BusDaoImpl();
    private final UserDao userDao = new UserDaoImpl();
    private final OrderDao orderDao = new OrderDaoImpl();
    private final TripDao tripDao = new TripDaoImpl();
    private final BillDao billDao = new BillDaoImpl();

    private DaoFactory() {
    }

    /**
     * Gets bus dao.
     *
     * @return the bus dao
     */
    public BusDao getBusDao() {
        return busDao;
    }

    /**
     * Gets user dao.
     *
     * @return the user dao
     */
    public UserDao getUserDao() {
        return userDao;
    }

    /**
     * Gets order dao.
     *
     * @return the order dao
     */
    public OrderDao getOrderDao() {
        return orderDao;
    }

    /**
     * Gets trip dao.
     *
     * @return the trip dao
     */
    public TripDao getTripDao() {
        return tripDao;
    }

    /**
     * Gets bill dao.
     *
     * @return the bill dao
     */
    public BillDao getBillDao() {
        return billDao;
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static DaoFactory getInstance() {
        return INSTANCE;
    }
}
