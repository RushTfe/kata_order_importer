package com.pgalindo.kata.order.importer.usecase.get.download;

import com.pgalindo.kata.order.importer.usecase.post.importorders.ImportOrdersUseCase;
import com.pgalindo.kata.order.importer.utils.CsvGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@Component
public class OrdersDownloadCsvUseCase {

    private static final Logger logger = LoggerFactory.getLogger(OrdersDownloadCsvUseCase.class);

    public void generateCsv(PrintWriter writer) {

        List<String> headers = Arrays.asList("Nombre", "Edad", "Ciudad");
        List<List<String>> datos = Arrays.asList(
                Arrays.asList("Juan", "25", "Ciudad A"),
                Arrays.asList("María", "30", "Ciudad B"),
                Arrays.asList("Carlos", "28", "Ciudad C")
        );
        try {
            CsvGenerator csvGenerator = new CsvGenerator(writer, headers);

            datos.forEach(line -> {
                try {
                    csvGenerator.generateCsvLine(line);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            csvGenerator.finishProcess();

        } catch (IOException e) {
            logger.error("There was an error when trying to write the CSV", e);
        }
    }
}
