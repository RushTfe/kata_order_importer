package com.pgalindo.kata.order.importer.model.client;

import java.util.List;

public record EspublicoClientResponse(
        Integer page,
        List<OrderClientResponse> content,
        OrderClientLinksResponse links
) {
}
