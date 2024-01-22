package com.pgalindo.kata.order.importer.usecase.post.importorders;

public record ImportOrdersUseCaseOutput(
        String result,
        Integer totalOrdersImported
) {
}
