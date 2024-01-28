package com.pgalindo.kata.order.importer.service.impl;

import com.pgalindo.kata.order.importer.model.OrderCsvLineDto;
import com.pgalindo.kata.order.importer.model.entity.*;
import com.pgalindo.kata.order.importer.model.helper.RelationCacheHelper;
import com.pgalindo.kata.order.importer.model.mapper.OrderMapper;
import com.pgalindo.kata.order.importer.model.service.OrderInput;
import com.pgalindo.kata.order.importer.repository.OrderRepository;
import com.pgalindo.kata.order.importer.service.*;
import com.pgalindo.kata.order.importer.utils.TimeUtils;
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
    public List<OrderCsvLineDto> findAllForCsv() {
        return orderRepository.findAllByOrderByOriginalOrderIdAsc().stream().map(orderEntity -> new OrderCsvLineDto(
                        orderEntity.getOriginalOrderId().toString(),
                        orderEntity.getPriority().getName(),
                        TimeUtils.localDateToCsvStringFormatted(orderEntity.getDate()),
                        orderEntity.getCountry().getRegion().getName(),
                        orderEntity.getCountry().getName(),
                        orderEntity.getItemType().getName(),
                        orderEntity.getSalesChannel().getName(),
                        TimeUtils.localDateToCsvStringFormatted(orderEntity.getShipDate()),
                        orderEntity.getUnitsSold(),
                        orderEntity.getUnitPrice(),
                        orderEntity.getUnitCost(),
                        orderEntity.getTotalRevenue(),
                        orderEntity.getTotalCost(),
                        orderEntity.getTotalProfit()
                ))
                .toList();
    }

    @Override
    public List<SummaryProjection> findCountrySummaries() {
        return orderRepository.findCountrySummaries();
    }

    @Override
    public List<SummaryProjection> findRegionSummaries() {
        return orderRepository.findRegionSummaries();
    }

    @Override
    public List<SummaryProjection> findItemtypeSummaries() {
        return orderRepository.findItemTypeSummaries();
    }

    @Override
    public List<SummaryProjection> findSalesChannelSummaries() {
        return orderRepository.findSalesChannelSummaries();
    }

    @Override
    public List<SummaryProjection> findPrioritySummaries() {
        return orderRepository.findPrioritySummaries();
    }

    @Override
    public void saveAll(List<OrderInput> orderInputs, RelationCacheHelper cacheHelper) {

        logger.info("Started order importing service. a total of {} orders will be saved.", orderInputs.size());

        long millisStartService = System.currentTimeMillis();

        List<Order> orderEntities = orderInputs
                .stream()
                .map(orderInput -> createNewOrder(orderInput, cacheHelper))
                .toList();

        orderRepository.saveAll(orderEntities);

        long millisEndService = System.currentTimeMillis();

        logger.info("Finished order importing service. Took {} seconds to execute.", TimeUtils.elapsedMillisToSeconds(millisEndService, millisStartService));
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
