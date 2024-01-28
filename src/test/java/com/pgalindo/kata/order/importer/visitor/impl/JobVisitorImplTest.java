package com.pgalindo.kata.order.importer.visitor.impl;

import com.pgalindo.kata.order.importer.model.entity.ClearDatabaseJobEntity;
import com.pgalindo.kata.order.importer.model.entity.ImportJobEntity;
import com.pgalindo.kata.order.importer.usecase.job.ClearDatabaseJobUseCase;
import com.pgalindo.kata.order.importer.usecase.job.ImportOrdersJobUseCase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
class JobVisitorImplTest {

    @Mock
    private ImportOrdersJobUseCase importOrdersJobUseCase;

    @Mock
    private ClearDatabaseJobUseCase clearDatabaseJobUseCase;

    private JobVisitorImpl jobVisitor;

    @BeforeEach
    void setVisitor() {
        jobVisitor = new JobVisitorImpl(importOrdersJobUseCase, clearDatabaseJobUseCase);
    }

    @Test
    void testImportUseOrdersUseCaseCalledOk() {
        ImportJobEntity job = new ImportJobEntity();

        jobVisitor.visit(job);

        Mockito.verify(importOrdersJobUseCase, Mockito.times(1)).importOrders();
        Mockito.verify(clearDatabaseJobUseCase, Mockito.times(0)).clearDatabase();
    }

    @Test
    void testClearDatabaseUseCaseCalledOk() {
        ClearDatabaseJobEntity job = new ClearDatabaseJobEntity();

        jobVisitor.visit(job);

        Mockito.verify(clearDatabaseJobUseCase, Mockito.times(1)).clearDatabase();
        Mockito.verify(importOrdersJobUseCase, Mockito.times(0)).importOrders();
    }
}
