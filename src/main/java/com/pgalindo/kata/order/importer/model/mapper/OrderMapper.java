package com.pgalindo.kata.order.importer.model.mapper;

import com.pgalindo.kata.order.importer.model.entity.*;
import com.pgalindo.kata.order.importer.model.service.OrderInput;
import com.pgalindo.kata.order.importer.usecase.get.orders.response.OrderSummaryFieldModel;
import com.pgalindo.kata.order.importer.utils.NumberUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "priority", target = "priority")
    @Mapping(source = "salesChannel", target = "salesChannel")
    @Mapping(source = "itemType", target = "itemType")
    @Mapping(source = "country", target = "country")
    @Mapping(source = "orderInput.id", target = "originalOrderId")
    @Mapping(ignore = true, target = "id")
    Order orderInputToOrder(OrderInput orderInput,
                            Priority priority,
                            SalesChannel salesChannel,
                            ItemType itemType,
                            Country country);

    @Mapping(expression = "java(this.bigDecimalToString(projection.getTotalCost()))", target = "totalCost")
    @Mapping(expression = "java(this.bigDecimalToString(projection.getTotalProfit()))", target = "totalProfit")
    @Mapping(expression = "java(this.integerToString(projection.getTotalCount()))", target = "count")
    OrderSummaryFieldModel projectionToOrderSummaryFieldResponse(SummaryProjection projection);

    default String bigDecimalToString(BigDecimal number) {
        return NumberUtils.bigDecimalToUiString(number);
    }
    default String integerToString(Integer number) {
        return NumberUtils.integerToUiString(number);
    }

}
