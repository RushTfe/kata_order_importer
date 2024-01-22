package com.pgalindo.kata.order.importer.usecase.post.importorders;

import com.pgalindo.kata.order.importer.clients.EspublicoClient;
import com.pgalindo.kata.order.importer.model.client.EspublicoClientResponse;
import com.pgalindo.kata.order.importer.model.helper.RelationCacheHelper;
import com.pgalindo.kata.order.importer.model.mapper.ClientMapper;
import com.pgalindo.kata.order.importer.model.service.OrderInput;
import com.pgalindo.kata.order.importer.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImportOrdersUseCase {

    private static final Logger logger = LoggerFactory.getLogger(ImportOrdersUseCase.class);

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

        long millisStartUseCase = System.currentTimeMillis();

        logger.info("Se inicia caso de uso para importar órdenes. Max orders per page establecido a {} ordenes", MAX_ORDERS_PER_PAGE);

        RelationCacheHelper cacheHelper = new RelationCacheHelper();

        int page = 1;

        while (page <= 10) {
            logger.info("Se lanza petición a espublico");

            long millisBeforeClientRequest = System.currentTimeMillis();
            EspublicoClientResponse clientResponse = espublicoClient.getOrders(page, MAX_ORDERS_PER_PAGE);
            long millisAfterClientRequest = System.currentTimeMillis();

            logger.info("La petición ha tomado un total de {} millis en ejecutare", millisAfterClientRequest-millisBeforeClientRequest);

            List<OrderInput> orders = clientResponse.content()
                    .stream()
                    .map(clientMapper::orderClientResponseToOrderInput)
                    .toList();

            orderService.saveAll(orders, cacheHelper);

            page++;
        }

        long millisEndUseCase = System.currentTimeMillis();

        logger.info("Se finaliza el caso de uso para importar órdenes. Ha tomado un total de {} millis en ejecutarse", millisEndUseCase-millisStartUseCase);
    }
}
