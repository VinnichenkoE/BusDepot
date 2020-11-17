package com.vinnichenko.bdepot.util;

import com.vinnichenko.bdepot.exception.UtilException;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PasswordEncoderTest {

    @Test
    public void checkTest() {
        try {
            boolean condition = PasswordEncoder
                    .check("1234", "DsUpMDJOY4GermM+gBuAy8f+YPz4lC+WP8ojZtF0+88=\\$" +
                            "K90dtZCWgoSsBfZtec3TztPQA+9MNIcmAROuGHWAGuk=");
            assertTrue(condition);
        } catch (UtilException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = UtilException.class)
    public void checkTestException() throws UtilException {
        PasswordEncoder.check("1234", "");
    }
}