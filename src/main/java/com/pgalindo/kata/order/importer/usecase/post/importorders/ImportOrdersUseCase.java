package com.pgalindo.kata.order.importer.usecase.post.importorders;

import com.pgalindo.kata.order.importer.clients.EspublicoClient;
import com.pgalindo.kata.order.importer.model.client.EspublicoClientResponse;
import com.pgalindo.kata.order.importer.model.helper.RelationCacheHelper;
import com.pgalindo.kata.order.importer.model.mapper.ClientMapper;
import com.pgalindo.kata.order.importer.model.service.OrderInput;
import com.pgalindo.kata.order.importer.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImportOrdersUseCase {

    private final ClientMapper clientMapper;
    private final EspublicoClient espublicoClient;
    private final OrderService orderService;
    private final Integer MAX_ORDERS_PER_PAGE;

    @Autowired
    public ImportOrdersUseCase(ClientMapper clientMapper,
                               EspublicoClient espublicoClient,
                               OrderService orderService,
                               @Value("${koi.client.request.maxOrdersPerPage}") Integer maxOrdersPerPage) {
        this.clientMapper = clientMapper;
        this.espublicoClient = espublicoClient;
        this.orderService = orderService;
        this.MAX_ORDERS_PER_PAGE = maxOrdersPerPage;
    }

    public void importOrders() {

        RelationCacheHelper cacheHelper = new RelationCacheHelper();

        int page = 1;

        while (page <= 2500) {
            EspublicoClientResponse clientResponse = espublicoClient.getOrders(page, MAX_ORDERS_PER_PAGE);

            List<OrderInput> orders = clientResponse.content()
                    .stream()
                    .map(clientMapper::orderClientResponseToOrderInput)
                    .toList();

            orderService.saveAll(orders, cacheHelper);

            page++;
        }
    }
}
