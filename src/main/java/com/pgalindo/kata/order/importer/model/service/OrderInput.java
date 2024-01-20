package com.pgalindo.kata.order.importer.model.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record OrderInput(
        UUID uuid,
        Long id,
        String region,
        String country,
        String itemType,
        String salesChannel,
        String priority,
        LocalDate date,
        LocalDate shipDate,
        Integer unitsSold,
        BigDecimal unitPrice,
        BigDecimal unitCost,
        BigDecimal totalRevenue,
        BigDecimal totalCost,
        BigDecimal totalProfit
) {
}

