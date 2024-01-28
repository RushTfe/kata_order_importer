package com.pgalindo.kata.order.importer.utils;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class NumberUtils {

    public static String bigDecimalToUiString(BigDecimal number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

        return decimalFormat.format(number);

    }

    public static String integerToUiString(Integer number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        return decimalFormat.format(number);
    }
}
