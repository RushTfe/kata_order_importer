package com.pgalindo.kata.order.importer.model.mapper;

import com.pgalindo.kata.order.importer.model.entity.*;
import com.pgalindo.kata.order.importer.model.service.OrderInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "priority", target = "priority")
    @Mapping(source = "salesChannel", target = "salesChannel")
    @Mapping(source = "itemType", target = "itemType")
    @Mapping(source = "country", target = "country")
    Order orderInputToOrder(OrderInput orderInput,
                            Priority priority,
                            SalesChannel salesChannel,
                            ItemType itemType,
                            Country country);

}
