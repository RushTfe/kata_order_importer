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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ImportOrdersUseCase {

    private static final Logger logger = LoggerFactory.getLogger(ImportOrdersUseCase.class);

    private final ClientMapper clientMapper;
    private final EspublicoClient espublicoClient;
    private final OrderService orderService;
    private final Integer MAX_ORDERS_PER_PAGE;
    private final Integer PAGE_BUFFER;
    private final boolean IMPORT_ALL;
    private final Integer PAGES_TO_IMPORT = 300;

    @Autowired
    public ImportOrdersUseCase(ClientMapper clientMapper,
                               EspublicoClient espublicoClient,
                               OrderService orderService,
                               @Value("${koi.client.request.maxOrdersPerPage}") Integer maxOrdersPerPage,
                               @Value("${koi.client.request.page-buffer}") Integer pageBuffer,
                               @Value("${koi.client.request.import-all}")boolean importAll) {
        this.clientMapper = clientMapper;
        this.espublicoClient = espublicoClient;
        this.orderService = orderService;
        this.MAX_ORDERS_PER_PAGE = maxOrdersPerPage;
        PAGE_BUFFER = pageBuffer;
        IMPORT_ALL = importAll;
    }

    public void importOrders() {

        int batchSize = PAGE_BUFFER * MAX_ORDERS_PER_PAGE;
        long millisStartUseCase = System.currentTimeMillis();

        List<OrderInput> batch = new ArrayList<>();

        logger.info("Se inicia caso de uso para importar órdenes");
        logger.info("Batch size -> {}", batchSize);
        logger.info("Max orders per page -> {}", MAX_ORDERS_PER_PAGE);
        logger.info("Pages to import -> {}", PAGES_TO_IMPORT);
        logger.info("Total de órdenes a importar -> {}", MAX_ORDERS_PER_PAGE * PAGES_TO_IMPORT);

        RelationCacheHelper cacheHelper = new RelationCacheHelper();

        int page = 1;

        EspublicoClientResponse clientResponse = makeClientCall(page);
        batch.addAll(buildOrderInputs(clientResponse));

        page++;

        while (hasNextPage(page, clientResponse)) {

            clientResponse = makeClientCall(page);
            batch.addAll(buildOrderInputs(clientResponse));

            if (batch.size() == batchSize) {
                orderService.saveAll(batch, cacheHelper);
                batch.clear();
            }

            page++;
        }

        if (!batch.isEmpty()) {
            logger.info("Insert spare batch");
            orderService.saveAll(batch, cacheHelper);
            batch.clear();
        }

        long millisEndUseCase = System.currentTimeMillis();

        logger.info("Se finaliza el caso de uso para importar órdenes. Ha tomado un total de {} segundos en ejecutarse", toSeconds(millisEndUseCase, millisStartUseCase));
    }

    private boolean hasNextPage(int page, EspublicoClientResponse clientResponse) {
        if (IMPORT_ALL) {
            return Objects.nonNull(clientResponse.links().next());
        }
        return page <= PAGES_TO_IMPORT;
    }

    private EspublicoClientResponse makeClientCall(int page) {

        return espublicoClient.getOrders(page, MAX_ORDERS_PER_PAGE);

    }

    private List<OrderInput> buildOrderInputs(EspublicoClientResponse clientResponse) {
        return clientResponse.content()
                .stream()
                .map(clientMapper::orderClientResponseToOrderInput)
                .toList();
    }

    private float toSeconds(long millisAfter, long millisBefore) {
        return (float) (millisAfter - millisBefore) / 1000;
    }
}
