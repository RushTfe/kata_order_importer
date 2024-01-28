package com.pgalindo.kata.order.importer.usecase.get.orders.response;

import java.util.List;

public record SummariesModel(
        List<OrderSummaryFieldModel> regions,
        List<OrderSummaryFieldModel> countries,
        List<OrderSummaryFieldModel> itemTypes,
        List<OrderSummaryFieldModel> salesChannels,
        List<OrderSummaryFieldModel> orderPriorities
) {
}
