package com.pgalindo.kata.order.importer.service;

import com.pgalindo.kata.order.importer.model.service.OrderCsvLineDto;
import com.pgalindo.kata.order.importer.model.entity.SummaryProjection;
import com.pgalindo.kata.order.importer.model.helper.RelationCacheHelper;
import com.pgalindo.kata.order.importer.model.service.OrderInput;

import java.util.List;

public interface OrderService {
    List<OrderCsvLineDto> findAllForCsv();

    List<SummaryProjection> findCountrySummaries();

    List<SummaryProjection> findRegionSummaries();

    List<SummaryProjection> findItemtypeSummaries();

    List<SummaryProjection> findSalesChannelSummaries();

    List<SummaryProjection> findPrioritySummaries();

    Long countAllOrders();

    void saveAll(List<OrderInput> orders, RelationCacheHelper cacheHelper);
    void removeOrders();
}
