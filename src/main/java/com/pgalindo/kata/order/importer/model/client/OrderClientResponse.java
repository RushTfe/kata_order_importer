package com.pgalindo.kata.order.importer.model.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderClientResponse(
        UUID uuid,
        Long id,
        String region,
        String country,
        @JsonProperty(value = "item_type") String itemType,
        @JsonProperty(value = "sales_channel") String salesChannel,
        String priority,
        String date,
        @JsonProperty(value = "ship_date") String shipDate,
        @JsonProperty(value = "units_sold") Integer unitsSold,
        @JsonProperty(value = "unit_price") BigDecimal unitPrice,
        @JsonProperty(value = "unit_cost") BigDecimal unitCost,
        @JsonProperty(value = "total_revenue") BigDecimal totalRevenue,
        @JsonProperty(value = "total_cost") BigDecimal totalCost,
        @JsonProperty(value = "total_profit") BigDecimal totalProfit
) {
}
