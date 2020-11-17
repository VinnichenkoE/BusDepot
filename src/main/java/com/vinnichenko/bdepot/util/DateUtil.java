package com.vinnichenko.bdepot.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final double MILLISECONDS_IN_HOUR = 1000 * 60 * 60d;
    private static final String DATE_FORMAT = "dd.MM.yyyy HH:mm";

    public static long toLong(String stringDate) {
        LocalDateTime date = LocalDateTime.parse(stringDate, DateTimeFormatter.ISO_DATE_TIME);
        Instant instant = date.atZone(ZoneId.systemDefault()).toInstant();
        return instant.toEpochMilli();
    }

    public static String toDate(long date) {
        Instant instant = Instant.ofEpochMilli(date);
        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime().format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public static int hoursBetween(long startDate, long endDate) {
        long interval = endDate - startDate;
        return (int) Math.round(interval / MILLISECONDS_IN_HOUR);
    }
}
