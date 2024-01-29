package com.pgalindo.kata.order.importer.utils;

import com.pgalindo.kata.order.importer.exceptions.CsvLineSizeException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CsvGenerator {

    private final List<String> headers;
    private final CSVPrinter csvPrinter;

    public CsvGenerator(PrintWriter writer, List<String> headers) throws IOException {
        this.headers = headers;
        csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);

        csvPrinter.printRecord(headers);
    }

    public void generateCsvLine(List<String> line) throws IOException {

        validateLine(line);

        csvPrinter.printRecord(line);

    }

    public void finishProcess() throws IOException {
        csvPrinter.close();
    }

    private void validateLine(List<String> line) {
        if (line.size() != headers.size()) {
            String message = String.format(
                    "Line provided should have the same amount of items than header (Header -> %d, Line -> %d).",
                    headers.size(), line.size()
            );
            throw new CsvLineSizeException(message);
        }
    }

}
