package com.pgalindo.kata.order.importer.repository;

import com.pgalindo.kata.order.importer.model.entity.SalesChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalesChannelRepository extends JpaRepository<SalesChannel, Long> {
    Optional<SalesChannel> findByNameIgnoreCase(String salesChannel);

    @Modifying
    @Query(value = "DELETE FROM SalesChannel")
    void deleteSalesChannel();
}
