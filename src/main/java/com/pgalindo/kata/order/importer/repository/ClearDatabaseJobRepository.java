package com.pgalindo.kata.order.importer.repository;

import com.pgalindo.kata.order.importer.model.entity.ClearDatabaseJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClearDatabaseJobRepository extends JpaRepository<ClearDatabaseJobEntity, Long> {

}
