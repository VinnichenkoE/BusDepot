package com.vinnichenko.bdepot.model.dao;

import com.vinnichenko.bdepot.model.dao.impl.*;

public final class DaoFactory {
    private static final DaoFactory INSTANCE = new DaoFactory();

    private final BusDao busDao = new BusDaoImpl();
    private final UserDao userDao = new UserDaoImpl();
    private final OrderDao orderDao = new OrderDaoImpl();
    private final TripDao tripDao = new TripDaoImpl();
    private final BillDao billDao = new BillDaoImpl();

    private DaoFactory() {
    }

    public BusDao getBusDao() {
        return busDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public TripDao getTripDao() {
        return tripDao;
    }

    public BillDao getBillDao() {
        return billDao;
    }

    public static DaoFactory getInstance() {
        return INSTANCE;
    }
}
