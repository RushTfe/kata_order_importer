package com.pgalindo.kata.order.importer.usecase.post.importorders;

import com.pgalindo.kata.order.importer.model.entity.ImportJobEntity;
import com.pgalindo.kata.order.importer.model.enums.JobStatus;
import com.pgalindo.kata.order.importer.service.JobService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImportOrdersUseCase {
    private static final Logger logger = LoggerFactory.getLogger(ImportOrdersUseCase.class);

    private final JobService jobService;

    public void importOrdersProcess() {
        logger.info("Received petition to import orders.");
        ImportJobEntity job = new ImportJobEntity();
        job.setStatus(JobStatus.WAITING);
        jobService.createJob(job);
    }
}
