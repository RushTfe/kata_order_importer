package com.pgalindo.kata.order.importer.usecase.job;

import com.pgalindo.kata.order.importer.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClearDatabaseJobUseCase {
    private final OrderService orderService;

    public void clearDatabase() {
        orderService.removeOrders();
    }
}
