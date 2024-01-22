package com.pgalindo.kata.order.importer.model.mapper;

import com.pgalindo.kata.order.importer.clients.espublico.model.EspublicoOrderClientResponse;
import com.pgalindo.kata.order.importer.model.service.OrderInput;
import com.pgalindo.kata.order.importer.utils.TimeUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(expression = "java(this.convertToLocalDate(espublicoOrderClientResponse.date()))", target = "date")
    @Mapping(expression = "java(this.convertToLocalDate(espublicoOrderClientResponse.shipDate()))", target = "shipDate")
    OrderInput orderClientResponseToOrderInput(EspublicoOrderClientResponse espublicoOrderClientResponse);

    default LocalDate convertToLocalDate(String date) {
        return TimeUtils.clientStringToLocalDate(date);
    }
}
