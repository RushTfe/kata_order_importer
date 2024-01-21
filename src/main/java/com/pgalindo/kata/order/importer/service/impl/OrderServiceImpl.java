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
    public void saveAll(List<OrderInput> orderInputs, RelationCacheHelper cacheHelper) {
        // TODO PARA MAÑANA:
        // Pasar caché al caso de uso
        // Añadir dependencia de Feign client
        // Llamar al GET de Espublico (Con una cantidad moderada de datos, por ejemplo, unos 100 divididos en 2 páginas)
        // Testear si todo es correcto
        // Circuit breaker?

        List<Order> orderEntities = orderInputs
                .stream()
                .map(orderInput -> createNewOrder(orderInput, cacheHelper))
        .toList();

        orderRepository.saveAll(orderEntities);
    }

    private Order createNewOrder(OrderInput orderInput, RelationCacheHelper cacheHelper) {
        Priority priority = cacheHelper.getPriority(orderInput.priority(), priorityService::findPriorityOrCreate);
        SalesChannel salesChannel = cacheHelper.getSalesChannel(orderInput.salesChannel(), salesChannelService::findSalesChannelOrCreate);
        ItemType itemType = cacheHelper.getItemType(orderInput.itemType(), itemTypeService::findItemTypesOrCreate);
        Region region = cacheHelper.getRegion(orderInput.region(), regionService::findPriorityOrCreate);
        Country country = cacheHelper.getCountry(
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
