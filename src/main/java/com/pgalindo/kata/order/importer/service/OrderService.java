package com.pgalindo.kata.order.importer.service;

import com.pgalindo.kata.order.importer.model.OrderCsvLineDto;
import com.pgalindo.kata.order.importer.model.helper.RelationCacheHelper;
import com.pgalindo.kata.order.importer.model.service.OrderInput;

import java.util.List;

public interface OrderService {
    List<OrderCsvLineDto> findAllForCsv();

    void saveAll(List<OrderInput> orders, RelationCacheHelper cacheHelper);
    void removeOrders();
}
