package com.pgalindo.kata.order.importer.usecase.get.jobs.response;

import java.util.List;

public record JobsModel(
        List<JobFieldModel> imports,
        List<JobFieldModel> clearDatabase
) {
}
