package com.pgalindo.kata.order.importer.usecase.get.download;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class OrdersDownloadCsvController {

    private final OrdersDownloadCsvUseCase ordersDownloadCsvUseCase;

    @Autowired
    public OrdersDownloadCsvController(OrdersDownloadCsvUseCase ordersDownloadCsvUseCase) {
        this.ordersDownloadCsvUseCase = ordersDownloadCsvUseCase;
    }


    @GetMapping(value = "/api/koi/orders/download", produces = "text/csv")
    ResponseEntity<Void> ordersDownload(HttpServletResponse response) throws IOException {

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=orders.csv");
        response.setCharacterEncoding("UTF-8");

        ordersDownloadCsvUseCase.generateCsv(response.getWriter());

        return ResponseEntity.ok().body(null);

    }
}
