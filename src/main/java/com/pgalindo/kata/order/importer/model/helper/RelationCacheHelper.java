package com.pgalindo.kata.order.importer.model.helper;

import com.pgalindo.kata.order.importer.model.entity.*;

import java.util.HashMap;
import java.util.Map;

public record RelationCacheHelper(
        Map<String, Priority> priorityMap,
        Map<String, SalesChannel> salesChannelMap,
        Map<String, ItemType> itemTypeMap,
        Map<String, Region> regionMap,
        Map<String, Country> countryMap
) {
    public RelationCacheHelper(Map<String, Priority> priorityMap,
                               Map<String, SalesChannel> salesChannelMap,
                               Map<String, ItemType> itemTypeMap,
                               Map<String, Region> regionMap,
                               Map<String, Country> countryMap) {
        this.priorityMap = priorityMap;
        this.salesChannelMap = salesChannelMap;
        this.itemTypeMap = itemTypeMap;
        this.regionMap = regionMap;
        this.countryMap = countryMap;
    }

    public RelationCacheHelper() {
        this(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>());
    }
}
