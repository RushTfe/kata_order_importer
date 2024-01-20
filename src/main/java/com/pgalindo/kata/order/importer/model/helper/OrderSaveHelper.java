package com.pgalindo.kata.order.importer.model.helper;

import java.util.HashSet;
import java.util.Set;

public class OrderSaveHelper {
    private final Set<String> itemTypes;
    private final Set<String> priorities;
    private final Set<String> saleChannels;
    private final Set<String> regions;
    private final Set<CountryHelper> countries;

    public OrderSaveHelper() {
        itemTypes = new HashSet<>();
        priorities = new HashSet<>();
        saleChannels = new HashSet<>();
        regions = new HashSet<>();
        countries = new HashSet<>();
    }

    public Set<String> getItemTypes() {
        return itemTypes;
    }

    public Set<String> getPriorities() {
        return priorities;
    }

    public Set<String> getSaleChannels() {
        return saleChannels;
    }

    public Set<String> getRegions() {
        return regions;
    }

    public Set<CountryHelper> getCountries() {
        return countries;
    }
}
