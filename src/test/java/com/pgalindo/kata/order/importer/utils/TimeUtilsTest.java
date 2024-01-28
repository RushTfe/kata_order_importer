package com.pgalindo.kata.order.importer.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class TimeUtilsTest {

    private static final String CSV_FORMAT = "dd/MM/yyyy";
    private static final String CLIENT_FORMAT = "M/d/yyyy";
    private static final String CLIENT_DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

    @Test
    void clientStringToLocalDateTestOk() {
        String date = "1/23/2014";
        String expected = "2014-01-23";

        String result = Assertions.assertDoesNotThrow(() -> TimeUtils.clientStringToLocalDate(date)).toString();

        Assertions.assertEquals(expected, result);
    }
    @Test
    void clientStringToLocalDateNullTestOk() {
        String date = null;

        LocalDate result = Assertions.assertDoesNotThrow(() -> TimeUtils.clientStringToLocalDate(date));

        Assertions.assertNull(result);
    }

    @Test
    void localDateToCsvStringFormattedTestOk() {
        LocalDate initialDate = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CSV_FORMAT);
        String expected = initialDate.format(formatter);

        String result = Assertions.assertDoesNotThrow(() -> TimeUtils.localDateToCsvStringFormatted(initialDate));

        Assertions.assertEquals(expected, result);

    }

    @Test
    void localDateToCsvStringFormattedNullTestOk() {
        LocalDate date = null;

        String result = Assertions.assertDoesNotThrow(() -> TimeUtils.localDateToCsvStringFormatted(date));

        Assertions.assertNull(result);
    }

    @Test
    void localDateTimeToStringTestOk() {
        LocalDateTime initialDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CLIENT_DATE_TIME_FORMAT);

        String expected = initialDate.format(formatter);

        String result = Assertions.assertDoesNotThrow(() -> TimeUtils.localDateTimeToString(initialDate));

        Assertions.assertEquals(expected, result);
    }

    @Test
    void elapsedTimeMillisToSecondsTestOk() {
        long millisAfter = 10001000;
        long millisBefore = 10000000;
        float expected = 1f;

        float result = TimeUtils.elapsedMillisToSeconds(millisAfter, millisBefore);

        Assertions.assertEquals(expected, result);
    }
}
