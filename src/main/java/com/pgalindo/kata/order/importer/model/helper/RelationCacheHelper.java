package com.pgalindo.kata.order.importer.model.helper;

import com.pgalindo.kata.order.importer.model.entity.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

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

    public Priority getPriority(String priorityName, Function<? super String, ? extends Priority> mappingFunction) {
        return this.priorityMap.computeIfAbsent(priorityName, mappingFunction);
    }

    public SalesChannel getSalesChannel(String salesChannelName, Function<? super String, ? extends SalesChannel> mappingFunction) {
        return this.salesChannelMap.computeIfAbsent(salesChannelName, mappingFunction);
    }

    public ItemType getItemType(String itemTypeName, Function<? super String, ? extends ItemType> mappingFunction) {
        return this.itemTypeMap.computeIfAbsent(itemTypeName, mappingFunction);
    }

    public Region getRegion(String regionName, Function<? super String, ? extends Region> mappingFunction) {
        return this.regionMap.computeIfAbsent(regionName, mappingFunction);
    }

    public Country getCountry(String countryName, Function<? super String, ? extends Country> mappingFunction) {
        return countryMap().computeIfAbsent(countryName, mappingFunction);
    }
}
