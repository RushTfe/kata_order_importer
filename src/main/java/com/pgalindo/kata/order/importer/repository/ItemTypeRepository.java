package com.pgalindo.kata.order.importer.repository;

import com.pgalindo.kata.order.importer.model.entity.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemTypeRepository extends JpaRepository<ItemType, Long> {
    Optional<ItemType> findByNameIgnoreCase(String itemType);

    @Modifying
    @Query(value = "DELETE FROM ItemType")
    void deleteItemTypes();
}
