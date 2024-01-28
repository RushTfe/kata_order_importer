package com.pgalindo.kata.order.importer.usecase.post.importorders;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ImportOrdersController {
    private final ImportOrdersUseCase importOrdersUseCase;

    @PostMapping("/api/koi/orders/importOrders")
    public ResponseEntity<Void> importAllOrders() {

        importOrdersUseCase.importOrdersProcess();

        return ResponseEntity.ok().body(null);
    }
}
