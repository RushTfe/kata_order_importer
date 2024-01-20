package com.pgalindo.kata.order.importer.usecase.get.orders;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrdersUseCase {

    public List<String> getOrders() {
        return List.of("test1", "test2");
    }
}
