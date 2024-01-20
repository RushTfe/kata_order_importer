package com.pgalindo.kata.order.importer.usecase.get.orders;

import com.pgalindo.kata.order.importer.usecase.get.orders.response.OrderResponse;
import com.pgalindo.kata.order.importer.usecase.get.orders.response.OrdersResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
public class OrdersUseCase {

    public OrdersResponse getOrders() {
        return new OrdersResponse(
                List.of(
                        new OrderResponse(
                                UUID.randomUUID().toString(),
                                "M",
                                LocalDate.now().toString(),
                                "Europe",
                                "Spain",
                                "Fruits",
                                "SHOP",
                                LocalDate.now().toString(),
                                1,
                                BigDecimal.ONE,
                                BigDecimal.ONE,
                                BigDecimal.TEN,
                                BigDecimal.ONE,
                                BigDecimal.ONE)));
    }
}
