package com.pgalindo.kata.order.importer.usecase.get.orders;

import com.pgalindo.kata.order.importer.model.entity.SummaryProjection;
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

        List<OrderSummaryFieldResponse> regionSummaries = getSummaries(orderService.findRegionSummaries());

        List<OrderSummaryFieldResponse> countrySummaries = getSummaries(orderService.findCountrySummaries());

        List<OrderSummaryFieldResponse> itemTypes = getSummaries(orderService.findItemtypeSummaries());

        List<OrderSummaryFieldResponse> salesChannels = getSummaries(orderService.findSalesChannelSummaries());

        List<OrderSummaryFieldResponse> priorities = getSummaries(orderService.findPrioritySummaries());

        return new OrdersResponse(regionSummaries, countrySummaries, itemTypes, salesChannels, priorities);
    }

    private List<OrderSummaryFieldResponse> getSummaries(List<SummaryProjection> projections) {
        return projections
                .stream()
                .map(orderMapper::projectionToOrderSummaryFieldResponse)
                .toList();
    }
}
