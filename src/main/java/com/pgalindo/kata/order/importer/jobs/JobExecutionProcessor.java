package com.pgalindo.kata.order.importer.jobs;

import com.pgalindo.kata.order.importer.model.entity.AbstractJobEntity;
import com.pgalindo.kata.order.importer.model.enums.JobStatus;
import com.pgalindo.kata.order.importer.service.JobService;
import com.pgalindo.kata.order.importer.visitor.JobVisitor;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JobExecutionProcessor implements Job {
    private static final Logger logger = LoggerFactory.getLogger(JobExecutionProcessor.class);

    private final JobVisitor jobVisitor;
    private final JobService jobService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("Job execution processor fired.");

        jobService.findFirstWaiting()
                .ifPresent(this::manageJob);

        logger.info("Job execution processor finished.");
    }

    private void manageJob(AbstractJobEntity job) {
        try {
            logger.info("Found a candidate for executing");
            jobService.updateStatus(job, JobStatus.IN_PROCESS);
            jobService.createJob(job);

            job.accept(jobVisitor);

            jobService.updateStatus(job, JobStatus.FINISHED);
        } catch (Exception e) {
            logger.error("An exception occured while trying to process an import", e);
            jobService.updateStatus(job, JobStatus.ERROR);
        }
    }
}