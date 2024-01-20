package com.pgalindo.kata.order.importer.service;

import com.pgalindo.kata.order.importer.model.entity.Priority;
import com.pgalindo.kata.order.importer.model.service.OrderInput;

import java.util.List;

public interface PriorityService {
    Priority findPriorityOrCreate(String priorityName);
}
