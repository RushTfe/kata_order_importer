package com.pgalindo.kata.order.importer.service;

import com.pgalindo.kata.order.importer.model.entity.AbstractJobEntity;
import com.pgalindo.kata.order.importer.model.entity.ClearDatabaseJobEntity;
import com.pgalindo.kata.order.importer.model.entity.ImportJobEntity;
import com.pgalindo.kata.order.importer.model.enums.JobStatus;

import java.util.List;
import java.util.Optional;

public interface JobService {
    Optional<AbstractJobEntity> findFirstWaiting();
    void createJob(AbstractJobEntity job);
    void updateStatus(AbstractJobEntity job, JobStatus status);
    List<ImportJobEntity> findAllImports();
    List<ImportJobEntity> findAllImportsWaitingOrInProcess();
    List<ClearDatabaseJobEntity> findAllClearDatabase();
    List<ClearDatabaseJobEntity> findAllClearDatabaseWaitingOrInProcess();
}
