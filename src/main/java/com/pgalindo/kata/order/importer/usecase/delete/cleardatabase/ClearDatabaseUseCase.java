package com.pgalindo.kata.order.importer.usecase.delete.cleardatabase;

import com.pgalindo.kata.order.importer.model.entity.ClearDatabaseJobEntity;
import com.pgalindo.kata.order.importer.model.enums.JobStatus;
import com.pgalindo.kata.order.importer.service.JobService;
import com.pgalindo.kata.order.importer.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClearDatabaseUseCase {
    private static final Logger logger = LoggerFactory.getLogger(ClearDatabaseUseCase.class);

    private final JobService jobService;
    private final OrderService orderService;

    public void clearDatabaseProcess() {
        Long count = orderService.countAllOrders();
        List<ClearDatabaseJobEntity> jobsAlreadyCreated = jobService.findAllClearDatabaseWaitingOrInProcess();

        if (count > 0 && CollectionUtils.isEmpty(jobsAlreadyCreated)) {
            logger.info("Received petition to clear database.");

            ClearDatabaseJobEntity job = new ClearDatabaseJobEntity();
            job.setStatus(JobStatus.WAITING);
            jobService.createJob(job);
        } else {
            logger.info("There are no orders at database to delete");
        }
    }
}
