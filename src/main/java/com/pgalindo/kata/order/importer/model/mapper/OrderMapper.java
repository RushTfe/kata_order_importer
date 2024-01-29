package com.pgalindo.kata.order.importer.model.mapper;

import com.pgalindo.kata.order.importer.model.entity.*;
import com.pgalindo.kata.order.importer.model.service.OrderCsvLineDto;
import com.pgalindo.kata.order.importer.model.service.OrderInput;
import com.pgalindo.kata.order.importer.usecase.get.orders.response.OrderSummaryFieldModel;
import com.pgalindo.kata.order.importer.utils.NumberUtils;
import com.pgalindo.kata.order.importer.utils.TimeUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "order.originalOrderId", target = "orderId")
    @Mapping(source = "order.priority.name", target = "orderPriority")
    @Mapping(source = "order.country.region.name", target = "region")
    @Mapping(source = "order.country.name", target = "country")
    @Mapping(source = "order.itemType.name", target = "itemType")
    @Mapping(source = "order.salesChannel.name", target = "salesChannel")
    @Mapping(expression = "java(this.localDateToString(order.getDate()))", target = "orderDate")
    @Mapping(expression = "java(this.localDateToString(order.getShipDate()))", target = "shipDate")
    OrderCsvLineDto orderToOrderCsvLineDto(Order order);

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
    default String localDateToString(LocalDate localDate) {
        return TimeUtils.localDateToCsvStringFormatted(localDate);
    }

}
