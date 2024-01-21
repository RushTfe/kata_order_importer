package com.pgalindo.kata.order.importer.service;

import com.pgalindo.kata.order.importer.model.entity.Region;

public interface RegionService {
    Region findPriorityOrCreate(String regionName);
    void clearTable();
}
