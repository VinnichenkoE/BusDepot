package com.vinnichenko.bdepot.validator;

import com.vinnichenko.bdepot.util.DateUtil;

import java.time.Instant;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.vinnichenko.bdepot.model.ParameterKey.*;

public class DataValidator {
    private static final String LOGIN_REGEX = "[\\d\\p{LC}]{4,25}";
    private static final String PASSWORD_REGEX = "[\\d\\p{LC}]{4,16}";
    private static final String NAME_REGEX = "\\p{LC}{2,35}";
    private static final String SURNAME_REGEX = "[\\p{LC}-]{2,50}";
    private static final String PHONE_NUMBER_REGEX = "^375\\d{9}$";
    private static final String NUMBER_REGEX = "\\d+";
    private static final String DATE_REGEX = "^(20[2-5][0-9])-(0[1-9]|1[0-2])-(0[1-9]|[1,2][0-9]|3[0,1])" +
            "T([0-1][0-9]|2[0-3]):([0-5][0-9])$";
    private static final String POINT_NAME_REGEX = "[\\p{LC}\\-]{2,45}";
    private static final String BRAND_REGEX = "[\\d\\p{LC}]{3,30}";
    private static final String MODEL_REGEX = "[\\d\\p{LC}\\-]{1,30}";
    private static final String REGISTRATION_NUMBER_REGEX = "\\d{4}\\s\\p{LC}{2}-\\d";
    private static final String DECIMAL_REGEX = "\\d{1,7}.*\\d{0,2}";
    private static final String EMPTY = "";
    private static final int MIN_NUMBER_SEATS = 8;
    private static final int MAX_NUMBER_SEATS = 45;
    private static final int MIN_DISTANCE = 1;
    private static final int MAX_DISTANCE = 9_999;


    public static boolean checkParam(String param, String regex) {
        boolean result = false;
        if (param != null && regex != null) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(param);
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

    public static boolean checkNumberOfSeats(String numberOfSeats) {
        boolean result = false;
        if (checkParam(numberOfSeats, NUMBER_REGEX)) {
            int numberSeats = Integer.parseInt(numberOfSeats);
            result = numberSeats >= MIN_NUMBER_SEATS
                    && numberSeats <= MAX_NUMBER_SEATS;
        }
        return result;
    }

    public static boolean checkDate(String startDate, String endDate) {
        boolean result = false;
        if (checkParam(startDate, DATE_REGEX) && checkParam(endDate, DATE_REGEX)) {
            long now = Instant.now().toEpochMilli();
            long start = DateUtil.toLong(startDate);
            long end = DateUtil.toLong(endDate);
            result = start > now && end > now && end > start;
        }
        return result;
    }

    public static boolean checkDistance(String distance) {
        boolean result = false;
        if (checkParam(distance, NUMBER_REGEX)) {
            int dist = Integer.parseInt(distance);
            result = dist >= MIN_DISTANCE
                    && dist <= MAX_DISTANCE;
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
        if (!checkParam(parameters.get(START_POINT), POINT_NAME_REGEX)) {
            result = false;
            parameters.put(START_POINT, EMPTY);
        }
        if (!checkParam(parameters.get(END_POINT), POINT_NAME_REGEX)) {
            result = false;
            parameters.put(END_POINT, EMPTY);
        }
        if (!checkDistance(parameters.get(DISTANCE))) {
            result = false;
            parameters.put(DISTANCE, EMPTY);
        }
        return result;
    }

    public static boolean checkUserData(Map<String, String> parameters) {
        boolean result = true;
        if (parameters.containsKey(USER_LOGIN)) {
            if (!checkParam(parameters.get(USER_LOGIN), LOGIN_REGEX)) {
                parameters.put(USER_LOGIN, EMPTY);
                result = false;
            }
        }
        if (parameters.containsKey(USER_PASSWORD)) {
            if (!checkPassword(parameters.get(USER_PASSWORD))) {
                parameters.put(USER_PASSWORD, EMPTY);
                result = false;
            }
        }
        if (!checkParam(parameters.get(USER_NAME), NAME_REGEX)) {
            parameters.put(USER_NAME, EMPTY);
            result = false;
        }
        if (!checkParam(parameters.get(USER_SURNAME), SURNAME_REGEX)) {
            parameters.put(USER_SURNAME, EMPTY);
            result = false;
        }
        if (!checkParam(parameters.get(USER_PHONE_NUMBER), PHONE_NUMBER_REGEX)) {
            parameters.put(USER_PHONE_NUMBER, EMPTY);
            result = false;
        }
        return result;
    }

    public static boolean checkBusData(Map<String, String> parameters) {
        boolean result = true;
        if (!checkParam(parameters.get(BRAND), BRAND_REGEX)) {
            result = false;
            parameters.put(BRAND, EMPTY);
        }
        if (!checkParam(parameters.get(MODEL), MODEL_REGEX)) {
            result = false;
            parameters.put(MODEL, EMPTY);
        }
        if (!checkParam(parameters.get(REGISTRATION_NUMBER), REGISTRATION_NUMBER_REGEX)) {
            result = false;
            parameters.put(REGISTRATION_NUMBER, EMPTY);
        }
        if (!checkNumberOfSeats(parameters.get(NUMBER_OF_SEATS))) {
            result = false;
            parameters.put(NUMBER_OF_SEATS, EMPTY);
        }
        if (!checkParam(parameters.get(RATE), DECIMAL_REGEX)) {
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
