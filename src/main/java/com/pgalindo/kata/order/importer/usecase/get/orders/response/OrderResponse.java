package com.pgalindo.kata.order.importer.usecase.get.orders.response;

import java.math.BigDecimal;

public record OrderResponse(
        String uuid,
        String orderPriority,
        String orderDate,
        String region,
        String country,
        String itemType,
        String salesChannel,
        String shipDate,
        Integer unitsSold,
        BigDecimal unitPrice,
        BigDecimal unitCost,
        BigDecimal totalRevenue,
        BigDecimal totalCost,
        BigDecimal totalProfit
) {
}
