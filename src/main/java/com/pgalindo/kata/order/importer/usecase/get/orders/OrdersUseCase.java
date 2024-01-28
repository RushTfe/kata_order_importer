package com.pgalindo.kata.order.importer.usecase.get.orders;

import com.pgalindo.kata.order.importer.service.CountryService;
import com.pgalindo.kata.order.importer.service.OrderService;
import com.pgalindo.kata.order.importer.usecase.get.orders.response.OrderSummaryFieldResponse;
import com.pgalindo.kata.order.importer.usecase.get.orders.response.OrdersResponse;
import com.pgalindo.kata.order.importer.utils.NumberUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrdersUseCase {

    private final OrderService orderService;

    public OrdersResponse getOrders() {

        List<OrderSummaryFieldResponse> countrySummaries = orderService.findCountrySummaries()
                .stream()
                .map(projection -> new OrderSummaryFieldResponse(
                        NumberUtils.integerToUiString(projection.getTotalCount()),
                        NumberUtils.bigDecimalToUiString(projection.getTotalCost()),
                        NumberUtils.bigDecimalToUiString(projection.getTotalProfit()),
                        projection.getName()))
                .toList();

        return new OrdersResponse(null, countrySummaries, null, null, null);
    }
}
