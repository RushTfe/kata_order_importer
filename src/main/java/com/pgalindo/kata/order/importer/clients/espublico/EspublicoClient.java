package com.pgalindo.kata.order.importer.clients.espublico;

import com.pgalindo.kata.order.importer.clients.espublico.model.EspublicoClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "Espublico-Client", url = "https://kata-espublicotech.g3stiona.com:443/v1")
public interface EspublicoClient {

    @GetMapping("/orders")
    EspublicoClientResponse getOrders(
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "max-per-page") Integer maxPerPage);
}
