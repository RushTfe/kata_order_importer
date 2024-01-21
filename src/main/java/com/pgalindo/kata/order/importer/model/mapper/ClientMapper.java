package com.pgalindo.kata.order.importer.model.mapper;

import com.pgalindo.kata.order.importer.model.client.OrderClientResponse;
import com.pgalindo.kata.order.importer.model.service.OrderInput;
import com.pgalindo.kata.order.importer.utils.DateUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(expression = "java(this.convertToLocalDate(orderClientResponse.date()))", target = "date")
    @Mapping(expression = "java(this.convertToLocalDate(orderClientResponse.shipDate()))", target = "shipDate")
    OrderInput orderClientResponseToOrderInput(OrderClientResponse orderClientResponse);

    default LocalDate convertToLocalDate(String date) {
        return DateUtils.clientStringToLocalDate(date);
    }
}
