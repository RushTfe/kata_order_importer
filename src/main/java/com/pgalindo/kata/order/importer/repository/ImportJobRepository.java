package com.pgalindo.kata.order.importer.repository;

import com.pgalindo.kata.order.importer.model.entity.ImportJobEntity;
import com.pgalindo.kata.order.importer.model.enums.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImportJobRepository extends JpaRepository<ImportJobEntity, Long> {

    Optional<ImportJobEntity> findFirstByStatusOrderByCreatedAtAsc(JobStatus status);
}
