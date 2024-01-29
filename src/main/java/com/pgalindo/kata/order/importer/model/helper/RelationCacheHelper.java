package com.pgalindo.kata.order.importer.model.helper;

import com.pgalindo.kata.order.importer.model.entity.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Cache class.
 * <p>
 *     Its used to store master tables, so it's not necessary to call database many times to retrieve them when doing
 *     the import process. It's also useful, because at import process, we don't have these rows loaded at database yet.
 * </p>
 *
 * <p>
 *     This class will find for the cached object. If it's not cached yet, it will execute the function that returns
 *     said object passed as parameter for every method. It's supposed to be a service call that saves and/or
 *     retrieves the item.
 * </p>
 *
 * @param priorityMap
 * @param salesChannelMap
 * @param itemTypeMap
 * @param regionMap
 * @param countryMap
 */
public record RelationCacheHelper(
        Map<String, Priority> priorityMap,
        Map<String, SalesChannel> salesChannelMap,
        Map<String, ItemType> itemTypeMap,
        Map<String, Region> regionMap,
        Map<String, Country> countryMap
) {

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
