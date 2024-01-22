package com.pgalindo.kata.order.importer.usecase.post.importorders.response;

public record ImportOrdersResponse(
        String result,
        Integer totalOrdersImported
) {
}
