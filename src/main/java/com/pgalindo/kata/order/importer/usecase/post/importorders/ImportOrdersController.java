package com.pgalindo.kata.order.importer.usecase.post.importorders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImportOrdersController {

    private final ImportOrdersUseCase importOrdersUseCase;

    @Autowired
    public ImportOrdersController(ImportOrdersUseCase importOrdersUseCase) {
        this.importOrdersUseCase = importOrdersUseCase;
    }

    @PostMapping("/api/koi/orders/importOrders")
    public ResponseEntity<String> importAllOrders() {

        importOrdersUseCase.importOrders();

        return ResponseEntity.ok().body("Orders imported");
    }
}
