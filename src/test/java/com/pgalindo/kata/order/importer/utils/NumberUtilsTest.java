package com.pgalindo.kata.order.importer.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class NumberUtilsTest {

    @Test
    void bigDecimalToUiStringTestOk() {
        BigDecimal number = new BigDecimal("1000000");
        String expected = "1.000.000,00";

        String result = NumberUtils.bigDecimalToUiString(number);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void integerToUiStringTestOk() {
        Integer number = 1000000;
        String expected = "1.000.000";

        String result = NumberUtils.integerToUiString(number);

        Assertions.assertEquals(expected, result);
    }
}
