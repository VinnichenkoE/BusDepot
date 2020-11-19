package com.vinnichenko.bdepot.controller.command;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.vinnichenko.bdepot.controller.command.CommandName.*;

/**
 * The enum Command set.
 */
public enum CommandSet {
    /**
     * Guest command set.
     */
    GUEST(Stream.of(
            WELCOME_PAGE,
            REGISTRATION_PAGE,
            CHANGE_LANGUAGE,
            AUTHORIZATION,
            SAVE_USER).
            map(CommandName::getCommand).collect(Collectors.toSet())),
    /**
     * Customer command set.
     */
    CUSTOMER(Stream.of(
            WELCOME_PAGE,
            CHANGE_LANGUAGE,
            ACCOUNT_PAGE,
            ADD_ORDER_PAGE,
            SAVE_ORDER,
            VIEW_ORDERS,
            UPDATE_USER_PAGE,
            UPDATE_USER,
            CHANGE_PASSWORD,
            LOGOUT).
            map(CommandName::getCommand).collect(Collectors.toSet())),
    /**
     * Driver command set.
     */
    DRIVER(Stream.of(
            WELCOME_PAGE,
            CHANGE_LANGUAGE,
            ACCOUNT_PAGE,
            VIEW_BUSES,
            VIEW_ORDERS,
            VIEW_BUSES,
            UPDATE_USER_PAGE,
            UPDATE_USER,
            CHANGE_PASSWORD,
            CHANGE_BUS_STATUS,
            START_TRIP,
            VIEW_TRIPS,
            FINISH_TRIP,
            LOGOUT).
            map(CommandName::getCommand).collect(Collectors.toSet())),
    /**
     * Dispatcher command set.
     */
    DISPATCHER(Stream.of(
            WELCOME_PAGE,
            CHANGE_LANGUAGE,
            ACCOUNT_PAGE,
            VIEW_BUSES,
            ADD_BUS_PAGE,
            SAVE_BUS,
            REGISTRATION_PAGE,
            SAVE_USER,
            APPOINT_BUS,
            APPOINT_ORDER,
            VIEW_ORDERS,
            VIEW_BUSES,
            CHOOSE_DRIVER,
            APPOINT_DRIVER,
            UPDATE_USER,
            UPDATE_USER_PAGE,
            CHANGE_PASSWORD,
            VIEW_TRIPS,
            CHANGE_IS_ACTIVE,
            LOGOUT).
            map(CommandName::getCommand).collect(Collectors.toSet()));

    private Set<Command> commandSet;

    CommandSet(Set<Command> commandSet) {
        this.commandSet = commandSet;
    }

    /**
     * Gets command set.
     *
     * @return the command set
     */
    public Set<Command> getCommandSet() {
        return commandSet;
    }
}
