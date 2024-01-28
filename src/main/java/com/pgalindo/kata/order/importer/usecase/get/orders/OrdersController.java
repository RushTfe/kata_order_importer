package com.pgalindo.kata.order.importer.usecase.get.orders;

import com.pgalindo.kata.order.importer.usecase.get.orders.response.OrdersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrdersController {

    private final OrdersUseCase ordersUseCase;

    @Autowired
    public OrdersController(OrdersUseCase ordersUseCase) {
        this.ordersUseCase = ordersUseCase;
    }

    @GetMapping("/api/koi/orders")
    String getOrders() {

        return "orders";

    }
}
