package com.pgalindo.kata.order.importer.usecase.delete.cleardatabase;

import com.pgalindo.kata.order.importer.model.entity.ClearDatabaseJobEntity;
import com.pgalindo.kata.order.importer.service.JobService;
import com.pgalindo.kata.order.importer.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
class ClearDatabaseUseCaseTest {

    @Mock
    private JobService jobService;

    @Mock
    private OrderService orderService;

    private ClearDatabaseUseCase useCase;

    @BeforeEach
    void setUseCase() {
        useCase = new ClearDatabaseUseCase(jobService, orderService);
    }

    @Test
    void clearDatabaseProcessCountZeroOkTest() {
        Mockito.when(orderService.countAllOrders())
                .thenReturn(0L);

        useCase.clearDatabaseProcess();

        Mockito.verify(jobService, Mockito.times(0)).createJob(Mockito.any());

    }

    @Test
    void clearDatabaseProcessJobsAlreadyCreatedOkTest() {
        Mockito.when(orderService.countAllOrders())
                .thenReturn(1L);

        Mockito.when(jobService.findAllClearDatabaseWaitingOrInProcess())
                .thenReturn(List.of(new ClearDatabaseJobEntity()));

        useCase.clearDatabaseProcess();

        Mockito.verify(jobService, Mockito.times(0)).createJob(Mockito.any());

    }

    @Test
    void clearDatabaseProcessJobsOkTest() {
        Mockito.when(orderService.countAllOrders())
                .thenReturn(1L);

        Mockito.when(jobService.findAllClearDatabaseWaitingOrInProcess())
                .thenReturn(List.of());

        useCase.clearDatabaseProcess();

        Mockito.verify(jobService, Mockito.times(1)).createJob(Mockito.any());

    }

}
