package com.pgalindo.kata.order.importer.usecase.get.orders.response;

public record OrderSummaryFieldModel(
        String count,
        String totalCost,
        String totalProfit,
        String name
) {
}
