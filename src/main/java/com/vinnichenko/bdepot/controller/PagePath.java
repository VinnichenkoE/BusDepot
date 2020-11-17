package com.vinnichenko.bdepot.controller;

public class PagePath {
    public static final String INDEX = "/index.jsp";
    public static final String WELCOME_PAGE = "/jsp/welcome.jsp";
    public static final String ERROR_404 = "/jsp/error/error_404.jsp";
    public static final String ERROR_500 = "/jsp/error/error_500.jsp";
    public static final String ACCOUNT = "/jsp/account.jsp";
    public static final String VIEW_BUSES_PAGE = "/jsp/view_buses.jsp";
    public static final String ADD_BUS = "/jsp/add_bus.jsp";
    public static final String ADD_ORDER = "/jsp/add_order.jsp";
    public static final String REGISTRATION = "/jsp/registration.jsp";
    public static final String VIEW_ORDERS_PAGE = "/jsp/view_orders.jsp";
    public static final String APPOINT_DRIVER = "/jsp/appoint_driver.jsp";
    public static final String UPDATE_USER = "/jsp/update_user.jsp";
    public static final String VIEW_TRIPS = "/jsp/view_trips.jsp";
    public static final String APPOINT_BUS = "/jsp/appoint_bus.jsp";
    public static final String VIEW_USERS_PAGE = "/jsp/view_users.jsp";
    public static final String WELCOME = "controller?command=welcome_page";
    public static final String VIEW_BUSES = "controller?command=view_buses";
    public static final String VIEW_ORDERS = "controller?command=view_orders";
    public static final String VIEW_USERS = "controller?command=view_users";


    private PagePath() {
    }
}
