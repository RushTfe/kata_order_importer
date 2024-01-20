package com.pgalindo.kata.order.importer.service.impl;

import com.pgalindo.kata.order.importer.model.entity.*;
import com.pgalindo.kata.order.importer.model.helper.CountryHelper;
import com.pgalindo.kata.order.importer.model.helper.OrderSaveHelper;
import com.pgalindo.kata.order.importer.model.service.OrderInput;
import com.pgalindo.kata.order.importer.repository.OrderRepository;
import com.pgalindo.kata.order.importer.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        OrderSaveHelper orderSaveHelper = new OrderSaveHelper();

        Map<String, Priority> priorityMap = new HashMap<>();
        Map<String, SalesChannel> salesChannelMap = new HashMap<>();
        Map<String, ItemType> itemTypeMap = new HashMap<>();
        Map<String, Region> regionMap = new HashMap<>();
        Map<String, Country> countryMap = new HashMap<>();

        orders.forEach(order -> addToOrderRelationsSaveHelper(order, orderSaveHelper));

        orderSaveHelper.getPriorities()
                .forEach(priorityName ->
                        priorityMap.put(priorityName, priorityService.findPriorityOrCreate(priorityName))
                );

        orderSaveHelper.getSaleChannels()
                .forEach(salesChannelName ->
                        salesChannelMap.put(salesChannelName, salesChannelService.findSalesChannelOrCreate(salesChannelName))
                );

        orderSaveHelper.getItemTypes()
                .forEach(itemTypeName ->
                        itemTypeMap.put(itemTypeName, itemTypeService.findItemTypesOrCreate(itemTypeName))
                );

        orderSaveHelper.getRegions()
                .forEach(regionName ->
                        regionMap.put(regionName, regionService.findPriorityOrCreate(regionName))
                );

        orderSaveHelper.getCountries()
                .forEach(countryHelper ->
                        countryMap.put(
                                countryHelper.getCountryName(),
                                countryService.findCountryOrCreate(
                                        countryHelper.getCountryName(),
                                        regionMap.get(countryHelper.getRegionName())
                                )
                        )
                );

        List<Order> orderEntities = orders.stream()
                .map(order -> new Order(order.uuid(),
                        countryMap.get(order.country()),
                        itemTypeMap.get(order.itemType()),
                        salesChannelMap.get(order.salesChannel()),
                        priorityMap.get(order.priority()),
                        order.date(),
                        order.shipDate(),
                        order.unitsSold(),
                        order.unitPrice(),
                        order.unitCost(),
                        order.totalRevenue(),
                        order.totalCost(),
                        order.totalProfit()))
                .toList();

        orderRepository.saveAll(orderEntities);

    }

    private void addToOrderRelationsSaveHelper(OrderInput order, OrderSaveHelper orderSaveHelper) {
        orderSaveHelper.getItemTypes().add(order.itemType());
        orderSaveHelper.getPriorities().add(order.priority());
        orderSaveHelper.getRegions().add(order.region());
        orderSaveHelper.getSaleChannels().add(order.salesChannel());
        orderSaveHelper.getCountries().add(new CountryHelper(order.country(), order.region()));
    }
}
