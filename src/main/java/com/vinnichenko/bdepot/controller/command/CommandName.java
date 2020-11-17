package com.vinnichenko.bdepot.controller.command;

import com.vinnichenko.bdepot.controller.PagePath;
import com.vinnichenko.bdepot.controller.command.impl.*;
import com.vinnichenko.bdepot.controller.router.Router;

public enum CommandName {
    WELCOME_PAGE(new WelcomePage()),
    AUTHORIZATION(new Authorization()),
    CHANGE_LANGUAGE(new ChangeLanguage()),
    LOGOUT(new Logout()),
    ACCOUNT_PAGE((req, resp) -> new Router(PagePath.ACCOUNT)),
    ADD_BUS_PAGE((req, resp) -> new Router(PagePath.ADD_BUS)),
    SAVE_BUS(new SaveBus()),
    REGISTRATION_PAGE((req, resp) -> new Router(PagePath.REGISTRATION)),
    SAVE_USER(new SaveUser()),
    APPOINT_BUS(new AppointBus()),
    ADD_ORDER_PAGE((req, resp) -> new Router(PagePath.ADD_ORDER)),
    SAVE_ORDER(new SaveOrder()),
    APPOINT_ORDER(new AppointOrder()),
    VIEW_ORDERS(new ViewOrders()),
    VIEW_BUSES(new ViewBuses()),
    CHOOSE_DRIVER(new ChooseDriver()),
    APPOINT_DRIVER(new AppointDriver()),
    UPDATE_USER_PAGE(new UpdateUserPage()),
    UPDATE_USER(new UpdateUser()),
    CHANGE_PASSWORD(new ChangePassword()),
    CHANGE_BUS_STATUS(new ChangeBusStatus()),
    START_TRIP(new StartTrip()),
    VIEW_TRIPS(new ViewTrips()),
    FINISH_TRIP(new FinishTrip()),
    VIEW_USERS(new ViewUsers()),
    CHANGE_IS_ACTIVE(new ChangeIsActive());

    private final Command command;

    CommandName(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
