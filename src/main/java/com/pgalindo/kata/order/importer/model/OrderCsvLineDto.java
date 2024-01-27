package com.pgalindo.kata.order.importer.model;

import java.math.BigDecimal;

public record OrderCsvLineDto(
        String orderId,
        String orderPriority,
        String orderDate,
        String region,
        String country,
        String itemType,
        String salesChannel,
        String shipDate,
        int unitsSold,
        BigDecimal unitPrice,
        BigDecimal unitCost,
        BigDecimal totalRevenue,
        BigDecimal totalCost,
        BigDecimal totalProfit
        ) {
}
