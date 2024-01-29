package com.pgalindo.kata.order.importer.usecase.post.importorders;

import com.pgalindo.kata.order.importer.model.entity.ImportJobEntity;
import com.pgalindo.kata.order.importer.model.enums.JobStatus;
import com.pgalindo.kata.order.importer.service.JobService;
import com.pgalindo.kata.order.importer.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImportOrdersUseCase {
    private static final Logger logger = LoggerFactory.getLogger(ImportOrdersUseCase.class);

    private final JobService jobService;
    private final OrderService orderService;

    public void importOrdersProcess() {
        Long count = orderService.countAllOrders();

        List<ImportJobEntity> jobsAlreadyCreated = jobService.findAllImportsWaitingOrInProcess();

        if (count == 0 && CollectionUtils.isEmpty(jobsAlreadyCreated)) {
            logger.info("Received petition to import orders.");

            ImportJobEntity job = new ImportJobEntity();
            job.setStatus(JobStatus.WAITING);
            jobService.createJob(job);
        } else {
            logger.info("Orders won't be imported because there are already orders at database.");
        }
    }
}
