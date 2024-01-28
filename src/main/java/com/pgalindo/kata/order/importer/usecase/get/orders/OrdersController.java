package com.pgalindo.kata.order.importer.usecase.get.orders;

import com.pgalindo.kata.order.importer.usecase.get.orders.response.SummariesModel;
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

        SummariesModel summaries = ordersUseCase.generateSummaries();

        model.addAttribute("summaries", summaries);
        model.addAttribute("canImportOrders", ordersUseCase.canImportOrders());
        model.addAttribute("canClearDatabase", ordersUseCase.canClearDatabase());

        return "orders";

    }
}
