package com.pgalindo.kata.order.importer.service;

import com.pgalindo.kata.order.importer.model.entity.Country;
import com.pgalindo.kata.order.importer.model.entity.Region;

public interface CountryService {
    Country findCountryOrCreate(String countryName, Region region);
}
