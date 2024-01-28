package com.pgalindo.kata.order.importer.usecase.get.orders;

import com.pgalindo.kata.order.importer.usecase.get.orders.response.OrdersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrdersController {

    private final OrdersUseCase ordersUseCase;

    @Autowired
    public OrdersController(OrdersUseCase ordersUseCase) {
        this.ordersUseCase = ordersUseCase;
    }

    @GetMapping("/api/koi/orders")
    String getOrders(Model model) {

        OrdersResponse summaries = ordersUseCase.getOrders();

        model.addAttribute("summaries", summaries);

        return "orders";

    }
}
