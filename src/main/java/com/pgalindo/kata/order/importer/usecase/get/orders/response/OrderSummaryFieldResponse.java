package com.pgalindo.kata.order.importer.usecase.get.orders.response;

import java.math.BigDecimal;

public record OrderSummaryFieldResponse(
        String count,
        String totalCost,
        String totalProfit,
        String name
) {
}
