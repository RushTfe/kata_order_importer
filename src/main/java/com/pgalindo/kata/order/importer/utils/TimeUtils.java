package com.pgalindo.kata.order.importer.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class TimeUtils {

    private static final String CSV_FORMAT = "dd/MM/yyyy";
    private static final String CLIENT_FORMAT = "M/d/yyyy";
    private static final String CLIENT_DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

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

    public static String localDateTimeToString(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CLIENT_DATE_TIME_FORMAT);

        return  localDateTime.format(formatter);
    }

    public static float elapsedMillisToSeconds(long millisAfter, long millisBefore) {
        return (float) (millisAfter - millisBefore) / 1000;
    }
}
