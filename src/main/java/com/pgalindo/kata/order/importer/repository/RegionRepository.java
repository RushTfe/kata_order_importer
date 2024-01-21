package com.pgalindo.kata.order.importer.repository;

import com.pgalindo.kata.order.importer.model.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findByNameIgnoreCase(String regionName);

    @Modifying
    @Query(value = "DELETE FROM Region")
    void deleteRegions();
}
