package com.pgalindo.kata.order.importer.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TimeUtils {

    public static LocalDate clientStringToLocalDate(String date) {
        if (date == null) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        return LocalDate.parse(date, formatter);
    }

    public static float elapsedMillisToSeconds(long millisAfter, long millisBefore) {
        return (float) (millisAfter - millisBefore) / 1000;
    }
}
