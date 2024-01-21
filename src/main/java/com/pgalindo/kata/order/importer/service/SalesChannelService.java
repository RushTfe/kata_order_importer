package com.pgalindo.kata.order.importer.service;

import com.pgalindo.kata.order.importer.model.entity.SalesChannel;

public interface SalesChannelService {
    SalesChannel findSalesChannelOrCreate(String salesChannelName);
    void clearTable();
}
