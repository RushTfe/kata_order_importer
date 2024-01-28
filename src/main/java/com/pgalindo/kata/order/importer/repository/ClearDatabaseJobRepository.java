package com.pgalindo.kata.order.importer.repository;

import com.pgalindo.kata.order.importer.model.entity.ClearDatabaseJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClearDatabaseJobRepository extends JpaRepository<ClearDatabaseJobEntity, Long> {
    List<ClearDatabaseJobEntity> findAllByOrderByCreatedAtDesc();
}
