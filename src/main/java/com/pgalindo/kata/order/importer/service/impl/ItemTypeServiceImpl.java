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

    /**
     * <p>
     *     Finds and return any Item Type matching itemTypeName.
     *     If it doesn't find one, it will create a new one and return it.
     * </p>
     * @param itemTypeName - Item type name to find one
     * @return - The matching ItemType
     */
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
