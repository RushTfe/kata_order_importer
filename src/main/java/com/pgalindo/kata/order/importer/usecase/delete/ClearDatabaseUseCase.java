package com.pgalindo.kata.order.importer.usecase.delete;

import com.pgalindo.kata.order.importer.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClearDatabaseUseCase {
    private final OrderService orderService;

    @Autowired
    public ClearDatabaseUseCase(OrderService orderService) {
        this.orderService = orderService;
    }

    public void clearDatabase() {
        orderService.removeOrders();
    }
}
