package com.vinnichenko.bdepot.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * The type Date util.
 */
public final class DateUtil {

    private static final double MILLISECONDS_IN_HOUR = 1000 * 60 * 60d;
    private static final String DATE_FORMAT = "dd.MM.yyyy HH:mm";

    private DateUtil() {
    }

    /**
     * To long long.
     * Converts a String to a long value.
     *
     * @param stringDate the string date
     * @return the long
     */
    public static long toLong(String stringDate) {
        LocalDateTime date = LocalDateTime.parse(stringDate, DateTimeFormatter.ISO_DATE_TIME);
        Instant instant = date.atZone(ZoneId.systemDefault()).toInstant();
        return instant.toEpochMilli();
    }

    /**
     * To date string.
     * Converts a long to a String value.
     *
     * @param date the date
     * @return the string
     */
    public static String toDate(long date) {
        Instant instant = Instant.ofEpochMilli(date);
        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime().format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    /**
     * Hours between int.
     * Counts the number of hours between the start date and the end date
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the int
     */
    public static int hoursBetween(long startDate, long endDate) {
        long interval = endDate - startDate;
        return (int) Math.round(interval / MILLISECONDS_IN_HOUR);
    }
}
