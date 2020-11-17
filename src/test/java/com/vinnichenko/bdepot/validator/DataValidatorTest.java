package com.vinnichenko.bdepot.validator;

import org.testng.annotations.Test;

import java.util.HashMap;

import static com.vinnichenko.bdepot.model.ParameterKey.*;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class DataValidatorTest {


    @Test
    public void checkPasswordTestPositive() {
        boolean condition = DataValidator.checkPassword("1234");
        assertTrue(condition);
    }

    @Test
    public void checkPasswordTestNegative() {
        boolean condition = DataValidator.checkPassword("123");
        assertFalse(condition);
    }

    @Test
    public void checkNumberOfSeatsTestPositive() {
        boolean condition  = DataValidator.checkNumberOfSeats("16");
        assertTrue(condition);
    }

    @Test
    public void checkNumberOfSeatsTestNegative() {
        boolean condition  = DataValidator.checkNumberOfSeats("1w");
        assertFalse(condition);
    }

    @Test
    public void checkDateTestPositive() {
        boolean condition = DataValidator.checkDate("2021-11-16T10:00", "2021-11-16T12:00");
        assertTrue(condition);
    }

    @Test
    public void checkDateTestNegative() {
        boolean condition = DataValidator.checkDate("1987-11-16T10:00", "2021-11-16T16:00");
        assertFalse(condition);
    }

    @Test
    public void checkDistanceTestPositive() {
        boolean condition = DataValidator.checkDistance("452");
        assertTrue(condition);
    }

    @Test
    public void checkDistanceTestNegative() {
        boolean condition = DataValidator.checkDistance("-152");
    }

    @Test
    public void checkOrderDataTestPositive() {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put(START_DATE, "2021-12-16T10:00");
        parameters.put(END_DATE, "2021-12-16T16:00");
        parameters.put(START_POINT, "Minsk");
        parameters.put(END_POINT, "Polotsk");
        parameters.put(DISTANCE, "284");
        parameters.put(NUMBER_OF_SEATS, "35");
        boolean condition = DataValidator.checkOrderData(parameters);
        assertTrue(condition);
    }

    @Test
    public void checkOrderDataTestNegative() {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put(START_DATE, "2011.11.12 10:00");
        parameters.put(END_DATE, "2021-12-16T16:00");
        parameters.put(START_POINT, "Minsk");
        parameters.put(END_POINT, "Polotsk");
        parameters.put(DISTANCE, "11000");
        parameters.put(NUMBER_OF_SEATS, "5");
        boolean condition = DataValidator.checkOrderData(parameters);
        assertFalse(condition);
    }

    @Test
    public void checkUserDataTestPositive() {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put(LOGIN, "user");
        parameters.put(PASSWORD, "1234");
        parameters.put(USER_NAME, "Ivan");
        parameters.put(USER_SURNAME, "Ivanov");
        parameters.put(USER_PHONE_NUMBER, "375296548521");
        boolean condition = DataValidator.checkUserData(parameters);
        assertTrue(condition);
    }

    @Test
    public void checkUserDataNegative() {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put(LOGIN, "user");
        parameters.put(PASSWORD, "123");
        parameters.put(USER_NAME, "Ivan");
        parameters.put(USER_SURNAME, "Ivanov");
        parameters.put(USER_PHONE_NUMBER, "7296548521");
        boolean condition = DataValidator.checkUserData(parameters);
        assertFalse(condition);
    }

    @Test
    public void checkBusDataTestPositive() {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put(BRAND, "ГАЗель");
        parameters.put(MODEL, "NEXT");
        parameters.put(NUMBER_OF_SEATS, "16");
        parameters.put(REGISTRATION_NUMBER, "1234 AA-7");
        parameters.put(RATE, "44.50");
        boolean condition = DataValidator.checkBusData(parameters);
        assertTrue(condition);
    }

    @Test
    public void checkBusDataTestNegative() {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put(BRAND, "ГАЗель");
        parameters.put(MODEL, "NEXT");
        parameters.put(NUMBER_OF_SEATS, "16");
        parameters.put(REGISTRATION_NUMBER, "12343 AA-7");
        parameters.put(RATE, "44.50");
        boolean condition = DataValidator.checkBusData(parameters);
        assertFalse(condition);
    }

    @Test
    public void isNumberTestPositive() {
        boolean condition = DataValidator.isNumber("25");
        assertTrue(condition);
    }

    @Test
    public void isNumberTestNegative() {
        boolean condition = DataValidator.isNumber("1w3");
        assertFalse(condition);
    }
}