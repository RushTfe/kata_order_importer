package com.pgalindo.kata.order.importer.service.impl;

import com.pgalindo.kata.order.importer.model.entity.ItemType;
import com.pgalindo.kata.order.importer.repository.ItemTypeRepository;
import com.pgalindo.kata.order.importer.service.ItemTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemTypeServiceImpl implements ItemTypeService {

    private final ItemTypeRepository itemTypeRepository;

    @Override
    public ItemType findItemTypesOrCreate(String itemTypeName) {
        Optional<ItemType> itemType = itemTypeRepository.findByNameIgnoreCase(itemTypeName);

        if (itemType.isPresent()) {
            return itemType.get();
        }
        ItemType newItemType = new ItemType();
        newItemType.setName(itemTypeName);
        return itemTypeRepository.save(newItemType);
    }

    @Override
    public void clearTable() {
        itemTypeRepository.deleteItemTypes();
    }
}
