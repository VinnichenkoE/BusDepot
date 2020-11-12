package com.vinnichenko.bdepot.validator;

import com.vinnichenko.bdepot.util.DateConverter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.vinnichenko.bdepot.model.ParameterKey.*;

public class DataValidator {
    private static final String LOGIN_REGEX = "[\\d\\p{LC}]{2,12}";
    private static final String PASSWORD_REGEX = "[\\d\\p{LC}]{4,16}";
    private static final String NAME_REGEX = "\\p{LC}{2,35}";
    private static final String SURNAME_REGEX = "\\p{LC}{2,50}";
    private static final String PHONE_NUMBER_REGEX = "^(\\d{9}|\\d{12})$";
    private static final String NUMBER_REGEX = "\\d+";
    private static final String DATE_REGEX = "^(20[2-5][0-9])-(0[1-9]|1[0-2])-(0[1-9]|[1,2][0-9]|3[0,1])T([0-1][0-9]|2[0-3]):([0-5][0-9])$";
    private static final String POINT_NAME_REGEX = "\\p{LC}+[-]*\\p{LC}*";
    private static final String BRAND_REGEX = "[\\d\\p{LC}]{3,30}";
    private static final String MODEL_REGEX = "[\\d\\p{LC}\\-]{1,30}";
    private static final String REGISTRATION_NUMBER_REGEX = "\\d{4}\\s\\p{LC}{2}-\\d";
    private static final String DECIMAL_REGEX = "\\d+.*\\d*";
    private static final String EMPTY = "";
    private static final int MIN_NUMBER_SEATS = 8;
    private static final int MAX_NUMBER_SEATS = 45;
    private static final int MIN_DISTANCE = 1;
    private static final int MAX_DISTANCE = 10000;


    public static boolean checkLogin(String login) {
        boolean result = false;
        if (login != null) {
            Pattern pattern = Pattern.compile(LOGIN_REGEX);
            Matcher matcher = pattern.matcher(login);
            result = matcher.matches();
        }
        return result;
    }

    public static boolean checkPassword(String password) {
        boolean result = false;
        if (password != null) {
            Pattern pattern = Pattern.compile(PASSWORD_REGEX);
            Matcher matcher = pattern.matcher(password);
            result = matcher.matches();
        }
        return result;
    }

    public static boolean checkName(String name) {
        boolean result = false;
        if (name != null) {
            Pattern pattern = Pattern.compile(NAME_REGEX);
            Matcher matcher = pattern.matcher(name);
            result = matcher.matches();
        }
        return result;
    }

    public static boolean checkSurname(String surname) {
        boolean result = false;
        if (surname != null) {
            Pattern pattern = Pattern.compile(SURNAME_REGEX);
            Matcher matcher = pattern.matcher(surname);
            result = matcher.matches();
        }
        return result;
    }

    public static boolean checkPhoneNumber(String phoneNumber) {
        boolean result = false;
        if (phoneNumber != null) {
            Pattern pattern = Pattern.compile(PHONE_NUMBER_REGEX);
            Matcher matcher = pattern.matcher(phoneNumber);
            result = matcher.matches();
        }
        return result;
    }

    public static boolean checkNumberOfSeats(String numberOfSeats) {
        boolean result = false;
        if (isNumber(numberOfSeats)) {
            int numberSeats = Integer.parseInt(numberOfSeats);
            result = numberSeats >= MIN_NUMBER_SEATS
                    && numberSeats <= MAX_NUMBER_SEATS;
        }
        return result;
    }

    public static boolean isDate(String date) {
        boolean result = false;
        if (date != null) {
            Pattern pattern = Pattern.compile(DATE_REGEX);
            Matcher matcher = pattern.matcher(date);
            result = matcher.matches();
        }
        return result;
    }

    public static boolean checkDate(String startDate, String endDate) {
        boolean result = false;
        if (isDate(startDate) && isDate(endDate)) {
            long now = Instant.now().toEpochMilli();
            long start = DateConverter.toLong(startDate);
            long end = DateConverter.toLong(endDate);
            result = start > now && end > now && end > start;
        }
        return result;
    }

    public static boolean checkDistance(String distance) {
        boolean result = false;
        if (isNumber(distance)) {
            int dist = Integer.parseInt(distance);
            result = dist >= MIN_DISTANCE
                    && dist <= MAX_DISTANCE;
        }
        return result;
    }

    public static boolean checkPointName(String pointName) {
        boolean result = false;
        if (pointName != null) {
            Pattern pattern = Pattern.compile(POINT_NAME_REGEX);
            Matcher matcher = pattern.matcher(pointName);
            result = matcher.matches();
        }
        return result;
    }

    public static boolean checkBrand(String brand) {
        boolean result = false;
        if (brand != null) {
            Pattern pattern = Pattern.compile(BRAND_REGEX);
            Matcher matcher = pattern.matcher(brand);
            result = matcher.matches();
        }
        return result;
    }

    public static boolean checkModel(String model) {
        boolean result = false;
        if (model != null) {
            Pattern pattern = Pattern.compile(MODEL_REGEX);
            Matcher matcher = pattern.matcher(model);
            result = matcher.matches();
        }
        return result;
    }

    public static boolean checkRegistrationNumber(String registrationNumber) {
        boolean result = false;
        if (registrationNumber != null) {
            Pattern pattern = Pattern.compile(REGISTRATION_NUMBER_REGEX);
            Matcher matcher = pattern.matcher(registrationNumber);
            result = matcher.matches();
        }
        return result;
    }

    public static boolean checkRate(String rate) {
        boolean result = false;
        if (rate != null) {
            Pattern pattern = Pattern.compile(DECIMAL_REGEX);
            Matcher matcher = pattern.matcher(rate);
            result = matcher.matches();
        }
        return result;
    }

    public static boolean checkOrderData(Map<String, String> parameters) {
        boolean result = true;
        if (!checkNumberOfSeats(parameters.get(NUMBER_OF_SEATS))) {
            result = false;
            parameters.put(NUMBER_OF_SEATS, EMPTY);
        }
        if (!checkDate(parameters.get(START_DATE), parameters.get(END_DATE))) {
            result = false;
            parameters.put(START_DATE, EMPTY);
            parameters.put(END_DATE, EMPTY);
        }
        if (!checkPointName(parameters.get(START_POINT))) {
            result = false;
            parameters.put(START_POINT, EMPTY);
        }
        if (!checkPointName(parameters.get(END_POINT))) {
            result = false;
            parameters.put(END_POINT, EMPTY);
        }
        if (!checkDistance(parameters.get(DISTANCE))) {
            result = false;
            parameters.put(DISTANCE, EMPTY);
        }
        return result;
    }

    public static boolean checkUpdateData(Map<String, String> parameters) {
        boolean result = true;
        if (parameters.containsKey(USER_LOGIN)) {
            if (!checkLogin(parameters.get(USER_LOGIN))) {
                result = false;
                parameters.put(USER_LOGIN, EMPTY);
            }
        }
        if (!checkName(parameters.get(USER_NAME))) {
            result = false;
            parameters.put(USER_NAME, EMPTY);
        }
        if (!checkSurname(parameters.get(USER_SURNAME))) {
            result = false;
            parameters.put(USER_SURNAME, EMPTY);
        }
        if (!checkPhoneNumber(parameters.get(USER_PHONE_NUMBER))) {
            result = false;
            parameters.put(USER_PHONE_NUMBER, EMPTY);
        }
        return result;
    }

    public static boolean checkRegistrationData(Map<String, String> parameters) {
        boolean result = true;
        if (!checkLogin(parameters.get(USER_LOGIN))) {
            parameters.put(USER_LOGIN, EMPTY);
            result = false;
        }
        if (!checkPassword(parameters.get(USER_PASSWORD))) {
            parameters.put(USER_PASSWORD, EMPTY);
            result = false;
        }
        if (!checkName(parameters.get(USER_NAME))) {
            parameters.put(USER_NAME, EMPTY);
            result = false;
        }
        if (!checkSurname(parameters.get(USER_SURNAME))) {
            parameters.put(USER_SURNAME, EMPTY);
            result = false;
        }
        if (!checkPhoneNumber(parameters.get(USER_PHONE_NUMBER))) {
            parameters.put(USER_PHONE_NUMBER, EMPTY);
            result = false;
        }
        return result;
    }

    public static boolean checkBusData(Map<String, String> parameters) {
        boolean result = true;
        if (!checkBrand(parameters.get(BRAND))) {
            result = false;
            parameters.put(BRAND, EMPTY);
        }
        if (!checkModel(parameters.get(MODEL))) {
            result = false;
            parameters.put(MODEL, EMPTY);
        }
        if (!checkRegistrationNumber(parameters.get(REGISTRATION_NUMBER))) {
            result = false;
            parameters.put(REGISTRATION_NUMBER, EMPTY);
        }
        if (!checkNumberOfSeats(parameters.get(NUMBER_OF_SEATS))) {
            result = false;
            parameters.put(NUMBER_OF_SEATS, EMPTY);
        }
        if (!checkRate(parameters.get(RATE))) {
            result = false;
            parameters.put(RATE, EMPTY);
        }
        return result;
    }

    public static boolean isNumber(String number) {
        boolean result = false;
        if (number != null) {
            Pattern pattern = Pattern.compile(NUMBER_REGEX);
            Matcher matcher = pattern.matcher(number);
            result = matcher.matches();
        }
        return result;
    }
}
