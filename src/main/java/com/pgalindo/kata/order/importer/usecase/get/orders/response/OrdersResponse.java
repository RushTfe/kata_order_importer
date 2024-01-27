package com.pgalindo.kata.order.importer.usecase.get.orders.response;

import java.util.List;

public record OrdersResponse(List<OrderResponse> orders) {
}
