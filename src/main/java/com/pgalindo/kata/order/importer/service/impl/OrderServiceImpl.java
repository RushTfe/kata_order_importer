package com.pgalindo.kata.order.importer.service.impl;

import com.pgalindo.kata.order.importer.model.entity.*;
import com.pgalindo.kata.order.importer.model.helper.RelationCacheHelper;
import com.pgalindo.kata.order.importer.model.service.OrderInput;
import com.pgalindo.kata.order.importer.repository.OrderRepository;
import com.pgalindo.kata.order.importer.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        RelationCacheHelper relationCacheHelper = new RelationCacheHelper();

        List<Order> orderEntities = orderInputs
                .stream()
                .map(orderInput -> createNewOrder(orderInput, relationCacheHelper))
        .toList();

        orderRepository.saveAll(orderEntities);
    }

    private Order createNewOrder(OrderInput orderInput, RelationCacheHelper relationCacheHelper) {
        Priority priority = relationCacheHelper.priorityMap().computeIfAbsent(orderInput.priority(), priorityService::findPriorityOrCreate);

        SalesChannel salesChannel = relationCacheHelper.salesChannelMap().computeIfAbsent(orderInput.salesChannel(), salesChannelService::findSalesChannelOrCreate);

        ItemType itemType = relationCacheHelper.itemTypeMap().computeIfAbsent(orderInput.itemType(), itemTypeService::findItemTypesOrCreate);

        Region region = relationCacheHelper.regionMap().computeIfAbsent(orderInput.region(), regionService::findPriorityOrCreate);

        Country country = relationCacheHelper.countryMap().computeIfAbsent(
                orderInput.country(),
                countryName -> countryService.findCountryOrCreate(countryName, region)
        );

        return new Order(
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
    }
}
