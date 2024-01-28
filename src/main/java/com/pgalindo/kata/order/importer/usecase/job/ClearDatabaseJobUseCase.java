package com.pgalindo.kata.order.importer.usecase.job;

import com.pgalindo.kata.order.importer.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClearDatabaseJobUseCase {
    private final OrderService orderService;

    @Autowired
    public ClearDatabaseJobUseCase(OrderService orderService) {
        this.orderService = orderService;
    }

    public void clearDatabase() {
        orderService.removeOrders();
    }
}
