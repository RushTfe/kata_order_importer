package com.pgalindo.kata.order.importer.visitor.impl;

import com.pgalindo.kata.order.importer.model.entity.ClearDatabaseJobEntity;
import com.pgalindo.kata.order.importer.model.entity.ImportJobEntity;
import com.pgalindo.kata.order.importer.usecase.job.ClearDatabaseJobUseCase;
import com.pgalindo.kata.order.importer.usecase.job.ImportOrdersJobUseCase;
import com.pgalindo.kata.order.importer.visitor.JobVisitor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JobVisitorImpl implements JobVisitor {

    private final ImportOrdersJobUseCase importOrdersJobUseCase;
    private final ClearDatabaseJobUseCase clearDatabaseJobUseCase;

    @Override
    public void visit(ClearDatabaseJobEntity clearDatabaseJobEntity) {
        clearDatabaseJobUseCase.clearDatabase();
    }

    @Override
    public void visit(ImportJobEntity importJobEntity) {
        importOrdersJobUseCase.importOrders();
    }
}
