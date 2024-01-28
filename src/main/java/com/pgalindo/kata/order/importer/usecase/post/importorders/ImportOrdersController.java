package com.pgalindo.kata.order.importer.usecase.post.importorders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImportOrdersController {

    private final ImportOrdersUseCase importOrdersUseCase;

    @Autowired
    public ImportOrdersController(ImportOrdersUseCase importOrderUseCase) {
        this.importOrdersUseCase = importOrderUseCase;
    }

    @PostMapping("/api/koi/orders/importOrders")
    public ResponseEntity<Void> importAllOrders() {

        importOrdersUseCase.importOrdersProcess();

        return ResponseEntity.ok().body(null);
    }
}
