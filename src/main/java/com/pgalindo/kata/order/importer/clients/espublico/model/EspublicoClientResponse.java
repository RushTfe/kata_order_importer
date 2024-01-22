package com.pgalindo.kata.order.importer.clients.espublico.model;

import java.util.List;

public record EspublicoClientResponse(
        Integer page,
        List<EspublicoOrderClientResponse> content,
        EspublicoOrderClientLinksResponse links
) {
}
