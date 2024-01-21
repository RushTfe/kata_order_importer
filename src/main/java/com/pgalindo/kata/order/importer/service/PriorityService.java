package com.pgalindo.kata.order.importer.service;

import com.pgalindo.kata.order.importer.model.entity.Priority;

public interface PriorityService {
    Priority findPriorityOrCreate(String priorityName);
    void clearTable();
}
