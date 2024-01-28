package com.pgalindo.kata.order.importer.model.entity;

import java.math.BigDecimal;

public interface SummaryProjection {
    String getName();
    Integer getTotalCount();
    BigDecimal getTotalCost();
    BigDecimal getTotalProfit();
}
