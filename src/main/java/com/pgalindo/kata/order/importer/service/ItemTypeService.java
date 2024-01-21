package com.pgalindo.kata.order.importer.service;

import com.pgalindo.kata.order.importer.model.entity.ItemType;

public interface ItemTypeService {
    ItemType findItemTypesOrCreate(String itemTypeName);
    void clearTable();
}
