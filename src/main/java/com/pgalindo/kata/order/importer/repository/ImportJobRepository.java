package com.pgalindo.kata.order.importer.repository;

import com.pgalindo.kata.order.importer.model.entity.ImportJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImportJobRepository extends JpaRepository<ImportJobEntity, Long> {
    List<ImportJobEntity> findAllByOrderByCreatedAtDesc();
}
