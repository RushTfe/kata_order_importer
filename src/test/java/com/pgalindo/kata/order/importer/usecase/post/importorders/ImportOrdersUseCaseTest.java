package com.pgalindo.kata.order.importer.usecase.post.importorders;

import com.pgalindo.kata.order.importer.model.entity.ClearDatabaseJobEntity;
import com.pgalindo.kata.order.importer.model.entity.ImportJobEntity;
import com.pgalindo.kata.order.importer.service.JobService;
import com.pgalindo.kata.order.importer.service.OrderService;
import com.pgalindo.kata.order.importer.usecase.delete.cleardatabase.ClearDatabaseUseCase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
class ImportOrdersUseCaseTest {

    @Mock
    private JobService jobService;

    @Mock
    private OrderService orderService;

    private ImportOrdersUseCase useCase;

    @BeforeEach
    void setUseCase() {
        useCase = new ImportOrdersUseCase(jobService, orderService);
    }

    @Test
    void importOrdersProcessCountZeroOkTest() {
        Mockito.when(orderService.countAllOrders())
                .thenReturn(1L);

        useCase.importOrdersProcess();

        Mockito.verify(jobService, Mockito.times(0)).createJob(Mockito.any());

    }

    @Test
    void clearDatabaseProcessJobsAlreadyCreatedOkTest() {
        Mockito.when(orderService.countAllOrders())
                .thenReturn(0L);

        Mockito.when(jobService.findAllImportsWaitingOrInProcess())
                .thenReturn(List.of(new ImportJobEntity()));

        useCase.importOrdersProcess();

        Mockito.verify(jobService, Mockito.times(0)).createJob(Mockito.any());

    }

    @Test
    void clearDatabaseProcessJobsOkTest() {
        Mockito.when(orderService.countAllOrders())
                .thenReturn(0L);

        Mockito.when(jobService.findAllImportsWaitingOrInProcess())
                .thenReturn(List.of());

        useCase.importOrdersProcess();

        Mockito.verify(jobService, Mockito.times(1)).createJob(Mockito.any());

    }

}
