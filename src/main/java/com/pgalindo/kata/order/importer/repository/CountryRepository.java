package com.pgalindo.kata.order.importer.repository;

import com.pgalindo.kata.order.importer.model.entity.Country;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findByNameIgnoreCase(String countryName);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Country")
    void deleteCountries();
}
