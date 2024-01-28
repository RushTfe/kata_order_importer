package com.pgalindo.kata.order.importer.usecase.get.download;

import com.pgalindo.kata.order.importer.model.OrderCsvLineDto;
import com.pgalindo.kata.order.importer.service.OrderService;
import com.pgalindo.kata.order.importer.utils.CsvGenerator;
import com.pgalindo.kata.order.importer.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@Component
public class OrdersDownloadCsvUseCase {

    private static final Logger logger = LoggerFactory.getLogger(OrdersDownloadCsvUseCase.class);

    private final OrderService orderService;

    @Autowired
    public OrdersDownloadCsvUseCase(OrderService orderService) {
        this.orderService = orderService;
    }

    public void generateCsv(PrintWriter writer) {
        logger.info("Started use case for downloading CSV file.");

        List<String> headers = Arrays.asList(
                "orderId",
                "orderPriority",
                "orderDate",
                "region",
                "country",
                "itemType",
                "salesChannel",
                "shipDate",
                "unitsSold",
                "unitPrice",
                "unitCost",
                "totalRevenue",
                "totalCost",
                "totalProfit"
        );

        long startTimestamp = System.currentTimeMillis();
        List<OrderCsvLineDto> csvLines = orderService.findAllForCsv();
        long endTimestamp = System.currentTimeMillis();

        float elapsedTime = TimeUtils.elapsedMillisToSeconds(endTimestamp, startTimestamp);

        logger.info("Query took {} seconds to execute", elapsedTime);

        try {
            CsvGenerator csvGenerator = new CsvGenerator(writer, headers);

            csvLines.forEach(line -> {
                try {
                    csvGenerator.generateCsvLine(List.of(
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

                    ));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            csvGenerator.finishProcess();

        } catch (IOException e) {
            logger.error("There was an error when trying to write the CSV", e);
        }

        logger.info("Finished use case for downloading CSV file.");

    }
}
