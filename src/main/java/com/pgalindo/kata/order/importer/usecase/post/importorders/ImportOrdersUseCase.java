package com.pgalindo.kata.order.importer.usecase.post.importorders;

import com.pgalindo.kata.order.importer.clients.EspublicoClient;
import com.pgalindo.kata.order.importer.model.client.EspublicoClientResponse;
import com.pgalindo.kata.order.importer.model.helper.RelationCacheHelper;
import com.pgalindo.kata.order.importer.model.service.OrderInput;
import com.pgalindo.kata.order.importer.service.OrderService;
import com.pgalindo.kata.order.importer.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImportOrdersUseCase {

    private final EspublicoClient espublicoClient;
    private final OrderService orderService;

    @Autowired
    public ImportOrdersUseCase(EspublicoClient espublicoClient, OrderService orderService) {
        this.espublicoClient = espublicoClient;
        this.orderService = orderService;
    }

    public void importOrders() {

        RelationCacheHelper cacheHelper = new RelationCacheHelper();

        EspublicoClientResponse clientResponse = espublicoClient.getOrders(1, 10);

        List<OrderInput> orders = clientResponse.content().stream().map(orderClientResponse ->
            new OrderInput(
                    orderClientResponse.uuid(),
                    orderClientResponse.id(),
                    orderClientResponse.region(),
                    orderClientResponse.country(),
                    orderClientResponse.itemType(),
                    orderClientResponse.salesChannel(),
                    orderClientResponse.priority(),
                    DateUtils.clientStringToLocalDate(orderClientResponse.date()),
                    DateUtils.clientStringToLocalDate(orderClientResponse.shipDate()),
                    orderClientResponse.unitsSold(),
                    orderClientResponse.unitPrice(),
                    orderClientResponse.unitCost(),
                    orderClientResponse.totalRevenue(),
                    orderClientResponse.totalCost(),
                    orderClientResponse.totalProfit()
            )
        )
        .toList();

        orderService.saveAll(orders, cacheHelper);
    }
}
