package com.vinnichenko.bdepot.model.service;

import com.vinnichenko.bdepot.model.service.impl.*;

/**
 * The type Service factory.
 */
public final class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();
    private final BusService busService = new BusServiceImpl();
    private final TripService tripService = new TripServiceImpl();

    private ServiceFactory() {
    }

    /**
     * Gets user service.
     *
     * @return the user service
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * Gets order service.
     *
     * @return the order service
     */
    public OrderService getOrderService() {
        return orderService;
    }

    /**
     * Gets bus service.
     *
     * @return the bus service
     */
    public BusService getBusService() {
        return busService;
    }

    /**
     * Gets trip service.
     *
     * @return the trip service
     */
    public TripService getTripService() {
        return tripService;
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ServiceFactory getInstance() {
        return INSTANCE;
    }
}
