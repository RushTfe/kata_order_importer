package com.pgalindo.kata.order.importer.usecase.get.jobs.response;

public record JobFieldModel(
        String status,
        String createdAt,
        String updatedAt
) {
}
