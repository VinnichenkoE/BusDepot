package com.vinnichenko.bdepot.model.service;

import com.vinnichenko.bdepot.model.service.impl.*;

public final class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();
    private final BusService busService = new BusServiceImpl();
    private final TripService tripService = new TripServiceImpl();
    private final BillService billService = new BillServiceImpl();

    private ServiceFactory() {
    }

    public UserService getUserService() {
        return userService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public BusService getBusService() {
        return busService;
    }

    public TripService getTripService() {
        return tripService;
    }

    public BillService getBillService() {
        return billService;
    }

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }
}
