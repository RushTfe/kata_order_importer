package com.pgalindo.kata.order.importer.repository;

import com.pgalindo.kata.order.importer.model.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {
    Optional<Priority> findByNameIgnoreCase(String priority);

    @Modifying
    @Query(value = "DELETE FROM Priority")
    void deletePriorities();
}
