package com.pgalindo.kata.order.importer.service.impl;

import com.pgalindo.kata.order.importer.model.entity.SalesChannel;
import com.pgalindo.kata.order.importer.repository.SalesChannelRepository;
import com.pgalindo.kata.order.importer.service.SalesChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalesChannelServiceImpl implements SalesChannelService {

    private final SalesChannelRepository salesChannelRepository;

    /**
     * <p>
     *     Finds and return any Sales Channel matching salesChannelName.
     *     If it doesn't find one, it will create a new one and return it.
     * </p>
     * @param salesChannelName - Item type name to find one
     * @return - The matching SalesChannel
     */
    @Override
    public SalesChannel findSalesChannelOrCreate(String salesChannelName) {
        Optional<SalesChannel> salesChannel = salesChannelRepository.findByNameIgnoreCase(salesChannelName);

        if (salesChannel.isPresent()) {
            return salesChannel.get();
        }
        SalesChannel newSalesChannel = new SalesChannel();
        newSalesChannel.setName(salesChannelName);
        return salesChannelRepository.save(newSalesChannel);
    }

    @Override
    public void clearTable() {
        salesChannelRepository.deleteSalesChannel();
    }
}
