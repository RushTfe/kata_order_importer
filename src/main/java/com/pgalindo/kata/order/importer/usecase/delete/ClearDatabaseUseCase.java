package com.pgalindo.kata.order.importer.usecase.delete;

import com.pgalindo.kata.order.importer.model.entity.ClearDatabaseJobEntity;
import com.pgalindo.kata.order.importer.model.enums.JobStatus;
import com.pgalindo.kata.order.importer.service.JobService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClearDatabaseUseCase {
    private static final Logger logger = LoggerFactory.getLogger(ClearDatabaseUseCase.class);

    private final JobService jobService;

    public void clearDatabaseProcess() {
        logger.info("Received petition to clear database.");
        ClearDatabaseJobEntity job = new ClearDatabaseJobEntity();
        job.setStatus(JobStatus.WAITING);
        jobService.createJob(job);
    }
}
