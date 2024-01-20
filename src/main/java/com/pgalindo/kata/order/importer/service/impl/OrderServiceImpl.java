package com.pgalindo.kata.order.importer.service.impl;

import com.pgalindo.kata.order.importer.model.entity.*;
import com.pgalindo.kata.order.importer.model.service.OrderInput;
import com.pgalindo.kata.order.importer.repository.OrderRepository;
import com.pgalindo.kata.order.importer.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final PriorityService priorityService;
    private final SalesChannelService salesChannelService;
    private final ItemTypeService itemTypeService;
    private final RegionService regionService;
    private final CountryService countryService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            PriorityService priorityService,
                            SalesChannelService salesChannelService,
                            ItemTypeService itemTypeService,
                            RegionService regionService,
                            CountryService countryService) {
        this.orderRepository = orderRepository;
        this.priorityService = priorityService;
        this.salesChannelService = salesChannelService;
        this.itemTypeService = itemTypeService;
        this.regionService = regionService;
        this.countryService = countryService;
    }

    @Override
    public void saveAll(List<OrderInput> orderInputs) {
        List<Order> orderEntities = new ArrayList<>();

        Map<String, Priority> priorityMap = new HashMap<>();
        Map<String, SalesChannel> salesChannelMap = new HashMap<>();
        Map<String, ItemType> itemTypeMap = new HashMap<>();
        Map<String, Region> regionMap = new HashMap<>();
        Map<String, Country> countryMap = new HashMap<>();

        for (OrderInput orderInput : orderInputs) {
            Priority priority = priorityMap.computeIfAbsent(orderInput.priority(), priorityService::findPriorityOrCreate);

            SalesChannel salesChannel = salesChannelMap.computeIfAbsent(orderInput.salesChannel(), salesChannelService::findSalesChannelOrCreate);

            ItemType itemType = itemTypeMap.computeIfAbsent(orderInput.itemType(), itemTypeService::findItemTypesOrCreate);

            Region region = regionMap.computeIfAbsent(orderInput.region(), regionService::findPriorityOrCreate);

            Country country = countryMap.computeIfAbsent(
                    orderInput.country(),
                    countryName -> countryService.findCountryOrCreate(countryName, region)
            );

            Order orderEntity = new Order(
                    orderInput.uuid(),
                    country,
                    itemType,
                    salesChannel,
                    priority,
                    orderInput.date(),
                    orderInput.shipDate(),
                    orderInput.unitsSold(),
                    orderInput.unitPrice(),
                    orderInput.unitCost(),
                    orderInput.totalRevenue(),
                    orderInput.totalCost(),
                    orderInput.totalProfit()
            );

            orderEntities.add(orderEntity);
        }

        orderRepository.saveAll(orderEntities);
    }
}
