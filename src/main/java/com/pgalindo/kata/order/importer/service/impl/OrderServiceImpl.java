package com.pgalindo.kata.order.importer.service.impl;

import com.pgalindo.kata.order.importer.model.entity.*;
import com.pgalindo.kata.order.importer.model.helper.RelationCacheHelper;
import com.pgalindo.kata.order.importer.model.mapper.OrderMapper;
import com.pgalindo.kata.order.importer.model.service.OrderInput;
import com.pgalindo.kata.order.importer.repository.OrderRepository;
import com.pgalindo.kata.order.importer.service.*;
import com.pgalindo.kata.order.importer.usecase.post.importorders.ImportOrdersUseCase;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final PriorityService priorityService;
    private final SalesChannelService salesChannelService;
    private final ItemTypeService itemTypeService;
    private final RegionService regionService;
    private final CountryService countryService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderMapper orderMapper,
                            PriorityService priorityService,
                            SalesChannelService salesChannelService,
                            ItemTypeService itemTypeService,
                            RegionService regionService,
                            CountryService countryService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.priorityService = priorityService;
        this.salesChannelService = salesChannelService;
        this.itemTypeService = itemTypeService;
        this.regionService = regionService;
        this.countryService = countryService;
    }

    @Override
    public void saveAll(List<OrderInput> orderInputs, RelationCacheHelper cacheHelper) {

        long millisStartService = System.currentTimeMillis();

        logger.info("Se inicia servicio para importar batch de órdenes");

        List<Order> orderEntities = orderInputs
                .stream()
                .map(orderInput -> createNewOrder(orderInput, cacheHelper))
                .toList();

        orderRepository.saveAll(orderEntities);

        long millisEndService = System.currentTimeMillis();

        logger.info("Se finaliza servicio para importar batch de órdenes. Ha tomado un total de {} millis en ejecutarse", millisStartService - millisEndService);
    }

    @Override
    @Transactional
    public void removeOrders() {
        orderRepository.deleteOrders();
        countryService.clearTable();
        regionService.clearTable();
        itemTypeService.clearTable();
        priorityService.clearTable();
        salesChannelService.clearTable();
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

        return orderMapper.orderInputToOrder(orderInput, priority, salesChannel, itemType, country);
    }
}
