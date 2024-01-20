package com.pgalindo.kata.order.importer.usecase.post.importorders;

import com.pgalindo.kata.order.importer.model.service.OrderInput;
import com.pgalindo.kata.order.importer.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
public class ImportOrdersUseCase {

    private final OrderService orderService;

    @Autowired
    public ImportOrdersUseCase(OrderService orderService) {
        this.orderService = orderService;
    }

    public void importOrders() {

        List<OrderInput> orders = List.of(
                new OrderInput(
                        UUID.randomUUID(),
                        1L,
                        "Europe",
                        "Spain",
                        "Fruits",
                        "Offline",
                        "M",
                        LocalDate.now(),
                        LocalDate.now(),
                        2,
                        new BigDecimal("5"),
                        BigDecimal.ONE,
                        BigDecimal.TEN,
                        new BigDecimal("2"),
                        new BigDecimal("8")
                        ),
                new OrderInput(
                        UUID.randomUUID(),
                        1L,
                        "Europe",
                        "France",
                        "Fruits",
                        "Offline",
                        "M",
                        LocalDate.now(),
                        LocalDate.now(),
                        2,
                        new BigDecimal("5"),
                        BigDecimal.ONE,
                        BigDecimal.TEN,
                        new BigDecimal("2"),
                        new BigDecimal("8")
                )
        );

        orderService.saveAll(orders);
    }
}
