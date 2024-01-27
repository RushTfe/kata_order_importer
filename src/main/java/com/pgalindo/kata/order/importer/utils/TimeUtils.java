package com.pgalindo.kata.order.importer.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TimeUtils {

    private static final String CSV_FORMAT = "dd/mm/yyyy";
    private static final String CLIENT_FORMAT = "M/d/yyyy";

    public static LocalDate clientStringToLocalDate(String date) {
        if (date == null) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CLIENT_FORMAT);
        return LocalDate.parse(date, formatter);
    }

    public static String localDateToCsvStringFormatted(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CSV_FORMAT);

        return localDate.format(formatter);
    }

    public static float elapsedMillisToSeconds(long millisAfter, long millisBefore) {
        return (float) (millisAfter - millisBefore) / 1000;
    }
}
