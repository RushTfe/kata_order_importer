package com.pgalindo.kata.order.importer.usecase.get.orders.response;

import com.pgalindo.kata.order.importer.usecase.get.orders.response.OrderResponse;

import java.util.List;

public record OrdersResponse(List<OrderResponse> orders) {
}
