package com.pgalindo.kata.order.importer.usecase.get.orders;

import com.pgalindo.kata.order.importer.model.entity.SummaryProjection;
import com.pgalindo.kata.order.importer.model.mapper.OrderMapper;
import com.pgalindo.kata.order.importer.service.OrderService;
import com.pgalindo.kata.order.importer.usecase.get.orders.response.OrderSummaryFieldModel;
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

        List<OrderSummaryFieldModel> regionSummaries = generateSummaries(orderService.findRegionSummaries());

        List<OrderSummaryFieldModel> countrySummaries = generateSummaries(orderService.findCountrySummaries());

        List<OrderSummaryFieldModel> itemTypes = generateSummaries(orderService.findItemtypeSummaries());

        List<OrderSummaryFieldModel> salesChannels = generateSummaries(orderService.findSalesChannelSummaries());

        List<OrderSummaryFieldModel> priorities = generateSummaries(orderService.findPrioritySummaries());

        return new SummariesModel(regionSummaries, countrySummaries, itemTypes, salesChannels, priorities);
    }

    public boolean canImportOrders() {
        return orderService.countAllOrders() == 0;
    }

    public boolean canClearDatabase() {
        return orderService.countAllOrders() > 0;
    }


    private List<OrderSummaryFieldModel> generateSummaries(List<SummaryProjection> projections) {
        return projections
                .stream()
                .map(orderMapper::projectionToOrderSummaryFieldResponse)
                .toList();
    }
}
