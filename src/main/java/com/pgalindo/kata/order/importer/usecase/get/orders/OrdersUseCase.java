package com.pgalindo.kata.order.importer.usecase.get.orders;

import com.pgalindo.kata.order.importer.model.entity.SummaryProjection;
import com.pgalindo.kata.order.importer.model.mapper.OrderMapper;
import com.pgalindo.kata.order.importer.service.OrderService;
import com.pgalindo.kata.order.importer.usecase.get.orders.response.OrderSummaryFieldResponse;
import com.pgalindo.kata.order.importer.usecase.get.orders.response.SummariesModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrdersUseCase {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public SummariesModel generateSummaries() {

        List<OrderSummaryFieldResponse> regionSummaries = generateSummaries(orderService.findRegionSummaries());

        List<OrderSummaryFieldResponse> countrySummaries = generateSummaries(orderService.findCountrySummaries());

        List<OrderSummaryFieldResponse> itemTypes = generateSummaries(orderService.findItemtypeSummaries());

        List<OrderSummaryFieldResponse> salesChannels = generateSummaries(orderService.findSalesChannelSummaries());

        List<OrderSummaryFieldResponse> priorities = generateSummaries(orderService.findPrioritySummaries());

        return new SummariesModel(regionSummaries, countrySummaries, itemTypes, salesChannels, priorities);
    }

    public boolean canImportOrders() {
        return orderService.countAllOrders() == 0;
    }

    public boolean canClearDatabase() {
        return orderService.countAllOrders() > 0;
    }


    private List<OrderSummaryFieldResponse> generateSummaries(List<SummaryProjection> projections) {
        return projections
                .stream()
                .map(orderMapper::projectionToOrderSummaryFieldResponse)
                .toList();
    }
}
