package com.pgalindo.kata.order.importer.usecase.job;

import com.pgalindo.kata.order.importer.clients.espublico.EspublicoClient;
import com.pgalindo.kata.order.importer.clients.espublico.model.EspublicoClientResponse;
import com.pgalindo.kata.order.importer.model.helper.RelationCacheHelper;
import com.pgalindo.kata.order.importer.model.mapper.ClientMapper;
import com.pgalindo.kata.order.importer.model.service.OrderInput;
import com.pgalindo.kata.order.importer.service.OrderService;
import com.pgalindo.kata.order.importer.utils.TimeUtils;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Use case to import orders from Espublico api.
 *
 * <p>
 * This class should be configured using application.properties to define the following:
 * </p>
 *
 * <ol>
 *     <li>
 *         MAX_ORDERS_PER_PAGE: Parameter that will be sent to Espublico client. Will determine how many orders will be
 *         returned per page.
 *     </li>
 *     <li>
 *         PAGE_BUFFER: Defines how many pages will be loaded to buffer before inserting them to Database.
 *         Will be used to calculate max bufferSize.
 *     </li>
 *     <li>
 *         PAGES_TO_IMPORT: Defines how many pages will be imported from source
 *         (Mostly for testing purposes, only works if IMPORT_ALL is false)
 *     </li>
 *     <li>
 *         IMPORT_ALL: Defines if all orders should be imported
 *         (False for testing purposes, PAGES_TO_IMPORT will be used then)
 *     </li>
 * </ol>
 */
@Component
public class ImportOrdersJobUseCase {

    private static final Logger logger = LoggerFactory.getLogger(ImportOrdersJobUseCase.class);

    private final ClientMapper clientMapper;
    private final EspublicoClient espublicoClient;
    private final OrderService orderService;
    private final Integer maxOrdersPerPage;
    private final Integer pageBuffer;
    private final Integer pagesToImport;
    private final boolean importAll;

    @Autowired
    public ImportOrdersJobUseCase(ClientMapper clientMapper,
                                  EspublicoClient espublicoClient,
                                  OrderService orderService,
                                  @Value("${koi.client.request.maxOrdersPerPage}") Integer maxOrdersPerPage,
                                  @Value("${koi.client.request.page-buffer}") Integer pageBuffer,
                                  @Value("${koi.client.request.pages-to-import}") Integer pagesToImport,
                                  @Value("${koi.client.request.import-all}") boolean importAll) {
        this.clientMapper = clientMapper;
        this.espublicoClient = espublicoClient;
        this.orderService = orderService;
        this.maxOrdersPerPage = maxOrdersPerPage;
        this.pageBuffer = pageBuffer;
        this.pagesToImport = pagesToImport;
        this.importAll = importAll;
    }

    /**
     * Calls Espublico API to populate a buffer, that will be sent to a service in charge of storing them in Database.
     * Uses a cache system to store relationships that will be used in orders later. This way, database will not be called
     * many times for each order to get them before saving.
     */
    @Transactional
    public void importOrders() {

        int batchSize = pageBuffer * maxOrdersPerPage;
        long millisStartUseCase = System.currentTimeMillis();

        List<OrderInput> batch = new ArrayList<>();

        logger.info("Started order import use case.");

        logger.info("Batch size -> {}", batchSize);
        logger.info("Max orders per page -> {}", maxOrdersPerPage);
        logger.info("Pages to import -> {}", pagesToImport);
        logger.info("Total de órdenes a importar -> {}", maxOrdersPerPage * pagesToImport);

        RelationCacheHelper cacheHelper = new RelationCacheHelper();

        int page = 1;
        EspublicoClientResponse clientResponse;

        do {
            logger.info("Loading to buffer page {}...", page);
            clientResponse = makeClientCall(page);
            batch.addAll(buildOrderInputs(clientResponse));

            page++;

            if (batch.size() == batchSize || !hasNextPage(page, clientResponse)) {
                orderService.saveAll(batch, cacheHelper);
                batch.clear();
            }
        }
        while (hasNextPage(page, clientResponse));

        long millisEndUseCase = System.currentTimeMillis();

        logger.info(
                "Finished order import use case. Took a total of {} seconds to execute.",
                TimeUtils.elapsedMillisToSeconds(millisEndUseCase, millisStartUseCase)
        );
    }

    private boolean hasNextPage(int page, EspublicoClientResponse clientResponse) {
        if (importAll) {
            return Objects.nonNull(clientResponse.links().next());
        }
        return page <= pagesToImport;
    }

    private EspublicoClientResponse makeClientCall(int page) {

        return espublicoClient.getOrders(page, maxOrdersPerPage);

    }

    private List<OrderInput> buildOrderInputs(EspublicoClientResponse clientResponse) {
        return clientResponse.content()
                .stream()
                .map(clientMapper::orderClientResponseToOrderInput)
                .toList();
    }
}
