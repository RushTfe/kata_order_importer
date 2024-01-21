package com.pgalindo.kata.order.importer.usecase.delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClearDatabaseController {

    private final ClearDatabaseUseCase clearDatabaseUseCase;

    @Autowired
    public ClearDatabaseController(ClearDatabaseUseCase clearDatabaseUseCase) {
        this.clearDatabaseUseCase = clearDatabaseUseCase;
    }

    @DeleteMapping("/api/koi/orders/clearDatabase")
    public ResponseEntity<String> clearDatabase() {

        clearDatabaseUseCase.clearDatabase();

        return ResponseEntity.ok().body("Orders imported");
    }
}
