package com.pgalindo.kata.order.importer.usecase.get.orders;

import com.pgalindo.kata.order.importer.usecase.get.orders.response.OrdersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersController {

    private final OrdersUseCase ordersUseCase;

    @Autowired
    public OrdersController(OrdersUseCase ordersUseCase) {
        this.ordersUseCase = ordersUseCase;
    }

    @GetMapping("/api/koi/orders")
    ResponseEntity<OrdersResponse> getOrders() {

        return ResponseEntity.ok().body(ordersUseCase.getOrders());

    }
}
