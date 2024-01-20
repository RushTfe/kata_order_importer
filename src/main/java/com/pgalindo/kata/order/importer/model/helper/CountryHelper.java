package com.pgalindo.kata.order.importer.model.helper;

import java.util.Objects;

public class CountryHelper {
    private final String countryName;
    private final String regionName;

    public CountryHelper(String countryName, String regionName) {
        this.countryName = countryName;
        this.regionName = regionName;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getRegionName() {
        return regionName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CountryHelper countryHelper = (CountryHelper) obj;
        return Objects.equals(countryName, countryHelper.countryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryName);
    }
}
