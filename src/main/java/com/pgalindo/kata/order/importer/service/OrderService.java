package com.pgalindo.kata.order.importer.service;

import com.pgalindo.kata.order.importer.model.service.OrderInput;

import java.util.List;

public interface OrderService {
    void saveAll(List<OrderInput> orders);
}