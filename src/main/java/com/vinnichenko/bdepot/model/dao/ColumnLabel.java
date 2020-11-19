package com.vinnichenko.bdepot.model.dao;

/**
 * The type Column label.
 * The class containing the names of the fields of all tables from the database
 */
public class ColumnLabel {

    //Columns users table
    /**
     * The constant USER_ID.
     */
    public static final String USER_ID = "user_id";
    /**
     * The constant LOGIN.
     */
    public static final String LOGIN = "login";
    /**
     * The constant PASSWORD.
     */
    public static final String PASSWORD = "password";
    /**
     * The constant IS_ACTIVE.
     */
    public static final String IS_ACTIVE = "is_active";
    /**
     * The constant NAME.
     */
    public static final String NAME = "name";
    /**
     * The constant SURNAME.
     */
    public static final String SURNAME = "surname";
    /**
     * The constant PHONE_NUMBER.
     */
    public static final String PHONE_NUMBER = "phone_number";
    /**
     * The constant USERS_ROLE_ID.
     */
    public static final String USERS_ROLE_ID = "role_id_fk";

    //Columns buses table
    /**
     * The constant BUS_ID.
     */
    public static final String BUS_ID = "bus_id";
    /**
     * The constant BRAND.
     */
    public static final String BRAND = "brand";
    /**
     * The constant MODEL.
     */
    public static final String MODEL = "model";
    /**
     * The constant REGISTRATION_NUMBER.
     */
    public static final String REGISTRATION_NUMBER = "registration_number";
    /**
     * The constant NUMBER_OF_SEATS.
     */
    public static final String NUMBER_OF_SEATS = "number_of_seats";
    /**
     * The constant RATE.
     */
    public static final String RATE = "rate";
    /**
     * The constant IMAGE_NAME.
     */
    public static final String IMAGE_NAME = "image_name";
    /**
     * The constant BUSES_BUS_STATUS_ID.
     */
    public static final String BUSES_BUS_STATUS_ID = "bus_status_id_fk";
    /**
     * The constant BUSES_USER_ID.
     */
    public static final String BUSES_USER_ID = "user_id_fk";

    //Columns orders table
    /**
     * The constant ORDER_ID.
     */
    public static final String ORDER_ID = "order_id";
    /**
     * The constant ORDERS_NUMBER_OF_SEATS.
     */
    public static final String ORDERS_NUMBER_OF_SEATS = "number_of_seats";
    /**
     * The constant START_DATE.
     */
    public static final String START_DATE = "start_date";
    /**
     * The constant END_DATE.
     */
    public static final String END_DATE = "end_date";
    /**
     * The constant START_POINT.
     */
    public static final String START_POINT = "start_point";
    /**
     * The constant END_POINT.
     */
    public static final String END_POINT = "end_point";
    /**
     * The constant DISTANCE.
     */
    public static final String DISTANCE = "distance";
    /**
     * The constant ORDERS_ORDER_STATUS_ID.
     */
    public static final String ORDERS_ORDER_STATUS_ID = "order_status_id_fk";

    //Columns trips table
    /**
     * The constant TRIP_ID.
     */
    public static final String TRIP_ID = "trip_id";
    /**
     * The constant TRIPS_START_DATE.
     */
    public static final String TRIPS_START_DATE = "start_date";
    /**
     * The constant TRIPS_END_DATE.
     */
    public static final String TRIPS_END_DATE = "end_date";
    /**
     * The constant COST.
     */
    public static final String COST = "cost";
    /**
     * The constant TRIPS_ORDER_ID.
     */
    public static final String TRIPS_ORDER_ID = "order_id_fk";
    /**
     * The constant TRIPS_USER_ID.
     */
    public static final String TRIPS_USER_ID = "user_id_fk";

    private ColumnLabel() {
    }
}
