package com.vinnichenko.bdepot.controller.command;

import com.vinnichenko.bdepot.controller.PagePath;
import com.vinnichenko.bdepot.controller.command.impl.*;
import com.vinnichenko.bdepot.controller.router.Router;

/**
 * The enum Command name.
 */
public enum CommandName {
    /**
     * The Welcome page.
     */
    WELCOME_PAGE(new WelcomePage()),
    /**
     * The Authorization.
     */
    AUTHORIZATION(new Authorization()),
    /**
     * The Change language.
     */
    CHANGE_LANGUAGE(new ChangeLanguage()),
    /**
     * The Logout.
     */
    LOGOUT(new Logout()),
    /**
     * The Account page.
     */
    ACCOUNT_PAGE((req, resp) -> new Router(PagePath.ACCOUNT)),
    /**
     * The Add bus page.
     */
    ADD_BUS_PAGE((req, resp) -> new Router(PagePath.ADD_BUS)),
    /**
     * The Save bus.
     */
    SAVE_BUS(new SaveBus()),
    /**
     * The Registration page.
     */
    REGISTRATION_PAGE((req, resp) -> new Router(PagePath.REGISTRATION)),
    /**
     * The Save user.
     */
    SAVE_USER(new SaveUser()),
    /**
     * The Appoint bus.
     */
    APPOINT_BUS(new AppointBus()),
    /**
     * The Add order page.
     */
    ADD_ORDER_PAGE((req, resp) -> new Router(PagePath.ADD_ORDER)),
    /**
     * The Save order.
     */
    SAVE_ORDER(new SaveOrder()),
    /**
     * The Appoint order.
     */
    APPOINT_ORDER(new AppointOrder()),
    /**
     * The View orders.
     */
    VIEW_ORDERS(new ViewOrders()),
    /**
     * The View buses.
     */
    VIEW_BUSES(new ViewBuses()),
    /**
     * The Choose driver.
     */
    CHOOSE_DRIVER(new ChooseDriver()),
    /**
     * The Appoint driver.
     */
    APPOINT_DRIVER(new AppointDriver()),
    /**
     * The Update user page.
     */
    UPDATE_USER_PAGE(new UpdateUserPage()),
    /**
     * The Update user.
     */
    UPDATE_USER(new UpdateUser()),
    /**
     * The Change password.
     */
    CHANGE_PASSWORD(new ChangePassword()),
    /**
     * The Change bus status.
     */
    CHANGE_BUS_STATUS(new ChangeBusStatus()),
    /**
     * The Start trip.
     */
    START_TRIP(new StartTrip()),
    /**
     * The View trips.
     */
    VIEW_TRIPS(new ViewTrips()),
    /**
     * The Finish trip.
     */
    FINISH_TRIP(new FinishTrip()),
    /**
     * The View users.
     */
    VIEW_USERS(new ViewUsers()),
    /**
     * The Change is active.
     */
    CHANGE_IS_ACTIVE(new ChangeIsActive());

    private final Command command;

    CommandName(Command command) {
        this.command = command;
    }

    /**
     * Gets command.
     *
     * @return the command
     */
    public Command getCommand() {
        return command;
    }
}
