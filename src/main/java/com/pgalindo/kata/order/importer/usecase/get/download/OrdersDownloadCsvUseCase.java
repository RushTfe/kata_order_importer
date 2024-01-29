package com.pgalindo.kata.order.importer.usecase.get.download;

import com.pgalindo.kata.order.importer.model.OrderCsvLineDto;
import com.pgalindo.kata.order.importer.model.enums.CsvHeaders;
import com.pgalindo.kata.order.importer.service.OrderService;
import com.pgalindo.kata.order.importer.utils.CsvGenerator;
import com.pgalindo.kata.order.importer.utils.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrdersDownloadCsvUseCase {
    private static Logger logger = LoggerFactory.getLogger(OrdersDownloadCsvUseCase.class);
    private final OrderService orderService;

    public void generateCsv(PrintWriter writer) {
        logger.info("Started use case for downloading CSV file.");

        Long count = orderService.countAllOrders();
        if (count == 0) {
            logger.info("There are no orders available at database to generate CSV. Please, import them first.");
            return;
        }

        List<String> headers = CsvHeaders.getHeaderValues();

        long startTimestamp = System.currentTimeMillis();
        List<OrderCsvLineDto> csvLines = orderService.findAllForCsv();
        long endTimestamp = System.currentTimeMillis();

        float elapsedTime = TimeUtils.elapsedMillisToSeconds(endTimestamp, startTimestamp);

        logger.info("Query took {} seconds to execute", elapsedTime);

        try {
            CsvGenerator csvGenerator = new CsvGenerator(writer, headers);

            csvLines.forEach(line -> {
                try {
                    csvGenerator.generateCsvLine(generateLine(line));
                } catch (IOException e) {
                    logger.error("There was an exception while trying to generate the CSV file.", e);
                }
            });

            csvGenerator.finishProcess();

        } catch (IOException e) {
            logger.error("There was an exception while trying to generate the CSV file", e);
        }

        logger.info("Finished use case for downloading CSV file.");

    }

    private static List<String> generateLine(OrderCsvLineDto line) {
        return List.of(
                line.orderId(),
                line.orderPriority(),
                line.orderDate(),
                line.region(),
                line.country(),
                line.itemType(),
                line.salesChannel(),
                line.shipDate(),
                String.valueOf(line.unitsSold()),
                line.unitPrice().toString(),
                line.unitCost().toString(),
                line.totalRevenue().toString(),
                line.totalCost().toString(),
                line.totalProfit().toString()
        );
    }
}
