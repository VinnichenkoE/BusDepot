package com.vinnichenko.bdepot.model.dao;

public class ColumnLabel {

    //Columns users table
    public static final String USER_ID = "user_id";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String IS_ACTIVE = "is_active";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String USERS_ROLE_ID = "role_id_fk";

    //Columns buses table
    public static final String BUS_ID = "bus_id";
    public static final String BRAND = "brand";
    public static final String MODEL = "model";
    public static final String REGISTRATION_NUMBER = "registration_number";
    public static final String NUMBER_OF_SEATS = "number_of_seats";
    public static final String RATE = "rate";
    public static final String IMAGE_NAME = "image_name";
    public static final String BUSES_BUS_STATUS_ID = "bus_status_id_fk";
    public static final String BUSES_USER_ID = "user_id_fk";

    //Columns orders table
    public static final String ORDER_ID = "order_id";
    public static final String ORDERS_NUMBER_OF_SEATS = "number_of_seats";
    public static final String START_DATE = "start_date";
    public static final String END_DATE = "end_date";
    public static final String START_POINT = "start_point";
    public static final String END_POINT = "end_point";
    public static final String DISTANCE = "distance";
    public static final String ORDERS_ORDER_STATUS_ID = "order_status_id_fk";

    //Columns trips table
    public static final String TRIP_ID = "trip_id";
    public static final String TRIPS_START_DATE = "start_date";
    public static final String TRIPS_END_DATE = "end_date";
    public static final String COST = "cost";
    public static final String TRIPS_ORDER_ID = "order_id_fk";
    public static final String TRIPS_USER_ID = "user_id_fk";

    //Columns bills table
    public static final String BILL_ID = "bill_id";
    public static final String BILLS_COST = "cost";
    public static final String IS_PAYED = "is_payed";
    public static final String BILLS_ORDER_ID = "order_id_fk";
    public static final String BILLS_USER_ID = "user_id_fk";

    //Columns user_roles table
    public static final String ROLE_ID = "role_id";
    public static final String ROLE_NAME = "role_name";

    //Columns bus_statuses table
    public static final String BUS_STATUS_ID = "bus_status_id";
    public static final String STATUS_NAME = "status_name";

    //Columns user_orders table
    public static final String ID = "id";
    public static final String USER_ORDERS_USER_ID = "user_id_fk";
    public static final String USER_ORDERS_ORDER_ID = "order_id_fk";

    //Columns order_statuses table
    public static final String ORDER_STATUS_ID = "order_status_id";
    public static final String ORDER_STATUSES_STATUS_NAME = "status_name";

    private ColumnLabel() {
    }
}
