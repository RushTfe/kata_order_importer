package com.pgalindo.kata.order.importer.usecase.get.orders;

import com.pgalindo.kata.order.importer.model.mapper.OrderMapper;
import com.pgalindo.kata.order.importer.service.OrderService;
import com.pgalindo.kata.order.importer.usecase.get.orders.response.OrderSummaryFieldResponse;
import com.pgalindo.kata.order.importer.usecase.get.orders.response.OrdersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrdersUseCase {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrdersResponse getOrders() {

        List<OrderSummaryFieldResponse> countrySummaries = orderService.findCountrySummaries()
                .stream()
                .map(orderMapper::projectionToOrderSummaryFieldResponse)
                .toList();

        return new OrdersResponse(null, countrySummaries, null, null, null);
    }
}
