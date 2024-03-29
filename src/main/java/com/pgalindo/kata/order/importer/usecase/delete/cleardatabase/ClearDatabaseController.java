package com.pgalindo.kata.order.importer.usecase.delete.cleardatabase;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClearDatabaseController {

    private final ClearDatabaseUseCase clearDatabaseUseCase;

    @DeleteMapping("/api/koi/orders/clear-database")
    public ResponseEntity<String> clearDatabase() {

        clearDatabaseUseCase.clearDatabaseProcess();

        return ResponseEntity.ok().body("Orders imported");
    }
}
