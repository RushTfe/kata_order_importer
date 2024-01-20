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
    public void saveAll(List<OrderInput> orders) {
        List<Order> orderEntities = new ArrayList<>();

        Map<String, Priority> priorityMap = new HashMap<>();
        Map<String, SalesChannel> salesChannelMap = new HashMap<>();
        Map<String, ItemType> itemTypeMap = new HashMap<>();
        Map<String, Region> regionMap = new HashMap<>();
        Map<String, Country> countryMap = new HashMap<>();

        for (OrderInput order : orders) {
            Priority priority = priorityMap.computeIfAbsent(order.priority(), priorityService::findPriorityOrCreate);

            SalesChannel salesChannel = salesChannelMap.computeIfAbsent(order.salesChannel(), salesChannelService::findSalesChannelOrCreate);

            ItemType itemType = itemTypeMap.computeIfAbsent(order.itemType(), itemTypeService::findItemTypesOrCreate);

            Region region = regionMap.computeIfAbsent(order.region(), regionService::findPriorityOrCreate);

            Country country = countryMap.computeIfAbsent(
                    order.country(),
                    countryName -> countryService.findCountryOrCreate(countryName, region)
            );


            Order orderEntity = new Order(
                    order.uuid(),
                    country,
                    itemType,
                    salesChannel,
                    priority,
                    order.date(),
                    order.shipDate(),
                    order.unitsSold(),
                    order.unitPrice(),
                    order.unitCost(),
                    order.totalRevenue(),
                    order.totalCost(),
                    order.totalProfit()
            );

            orderEntities.add(orderEntity);
        }

        orderRepository.saveAll(orderEntities);
    }
}
