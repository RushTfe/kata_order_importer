package com.pgalindo.kata.order.importer.exceptions;

public class CsvLineSizeException extends RuntimeException {

    public CsvLineSizeException(String message) {
        super(message);
    }
}
