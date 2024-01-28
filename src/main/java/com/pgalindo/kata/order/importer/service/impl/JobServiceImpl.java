package com.pgalindo.kata.order.importer.service.impl;

import com.pgalindo.kata.order.importer.model.entity.AbstractJobEntity;
import com.pgalindo.kata.order.importer.model.entity.ImportJobEntity;
import com.pgalindo.kata.order.importer.model.enums.JobStatus;
import com.pgalindo.kata.order.importer.repository.JobRepository;
import com.pgalindo.kata.order.importer.service.JobService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private static final Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);

    private final JobRepository jobRepository;

    @Override
    public Optional<ImportJobEntity> findFirstWaiting() {
        return jobRepository.findFirstByStatusOrderByCreatedAtAsc(JobStatus.WAITING);
    }

    @Override
    public void createJob(AbstractJobEntity job) {
        job.setCreatedAt(LocalDateTime.now());
        job.setUpdatedAt(LocalDateTime.now());
        jobRepository.save(job);
    }

    @Override
    public void updateStatus(AbstractJobEntity job, JobStatus status) {
        logger.info("Updating job status to {}", status);
        job.setStatus(status);
        job.setUpdatedAt(LocalDateTime.now());
    }
}
