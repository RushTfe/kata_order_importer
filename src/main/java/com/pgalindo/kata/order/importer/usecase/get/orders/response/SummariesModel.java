package com.pgalindo.kata.order.importer.usecase.get.orders.response;

import java.util.List;

public record SummariesModel(
        List<OrderSummaryFieldResponse> regions,
        List<OrderSummaryFieldResponse> countries,
        List<OrderSummaryFieldResponse> itemTypes,
        List<OrderSummaryFieldResponse> salesChannels,
        List<OrderSummaryFieldResponse> orderPriorities
) {
}
