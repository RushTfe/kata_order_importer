package com.pgalindo.kata.order.importer.service.impl;

import com.pgalindo.kata.order.importer.model.entity.Country;
import com.pgalindo.kata.order.importer.model.entity.Region;
import com.pgalindo.kata.order.importer.repository.CountryRepository;
import com.pgalindo.kata.order.importer.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Override
    public Country findCountryOrCreate(String countryName, Region region) {
        Optional<Country> country = countryRepository.findByNameIgnoreCase(countryName);

        if (country.isPresent()) {
            return country.get();
        }

        Country newCountry = new Country();
        newCountry.setName(countryName);
        newCountry.setRegion(region);
        return countryRepository.save(newCountry);
    }

    @Override
    public void clearTable() {
        countryRepository.deleteCountries();
    }
}
