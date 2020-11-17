package com.vinnichenko.bdepot.util;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DateUtilTest {

    @Test
    public void toLongTest() {
        long actual = DateUtil.toLong("2020-11-16T18:00");
        long expected = 1605538800000L;
        assertEquals(actual, expected);
    }

    @Test
    public void testToDate() {
        String actual = DateUtil.toDate(1605542400000L);
        String expected = "16.11.2020 19:00";
        assertEquals(actual, expected);
    }

    @Test
    public void testHoursBetween() {
        int actual = DateUtil.hoursBetween(1605538800000L, 1605542400000L);
        int expected = 1;
        assertEquals(actual, expected);
    }
}