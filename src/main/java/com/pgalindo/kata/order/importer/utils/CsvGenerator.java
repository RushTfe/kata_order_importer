package com.pgalindo.kata.order.importer.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CsvGenerator {

    private final List<String> encabezados;
    private final CSVPrinter csvPrinter;

    public CsvGenerator(PrintWriter writer, List<String> encabezados) throws IOException {
        this.encabezados = encabezados;
        csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);

        csvPrinter.printRecord(encabezados);
    }

    public void generateCsvLine(List<String> line) throws IOException {

        validateLine(line);

        csvPrinter.printRecord(line);

    }

    public void finishProcess() throws IOException {
        csvPrinter.close();
    }

    private void validateLine(List<String> line) {
        if (line.size() != encabezados.size()) {
            String message = String.format(
                    "Line provided should have the same amount of items than header (Header -> %d, Line -> %d).",
                    encabezados.size(), line.size()
            );
            throw new RuntimeException(message);
        }
    }

}
