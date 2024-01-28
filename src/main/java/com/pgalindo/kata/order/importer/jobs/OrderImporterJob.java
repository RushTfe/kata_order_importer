package com.pgalindo.kata.order.importer.jobs;

import com.pgalindo.kata.order.importer.model.entity.ImportJobEntity;
import com.pgalindo.kata.order.importer.model.enums.JobStatus;
import com.pgalindo.kata.order.importer.service.JobService;
import com.pgalindo.kata.order.importer.usecase.job.ImportOrdersJobUseCase;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderImporterJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(OrderImporterJob.class);

    private final ImportOrdersJobUseCase importOrdersJobUseCase;
    private final JobService jobService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("Executing Order import job.");

        jobService.findFirstWaiting()
                .ifPresent(this::manageJob);

        logger.info("Executed Order import job.");
    }

    private void manageJob(ImportJobEntity job) {
        try {
            logger.info("Found a candidate for importing orders");
            jobService.updateStatus(job, JobStatus.IN_PROCESS);
            jobService.createJob(job);
            importOrdersJobUseCase.importOrders();
            jobService.updateStatus(job, JobStatus.FINISHED);
        } catch (Exception e) {
            logger.error("An exception occured while trying to process an import", e);
            jobService.updateStatus(job, JobStatus.ERROR);
        }
    }
}