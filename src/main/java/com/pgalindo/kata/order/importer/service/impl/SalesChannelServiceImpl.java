package com.pgalindo.kata.order.importer.service.impl;

import com.pgalindo.kata.order.importer.model.entity.Priority;
import com.pgalindo.kata.order.importer.model.entity.SalesChannel;
import com.pgalindo.kata.order.importer.repository.PriorityRepository;
import com.pgalindo.kata.order.importer.repository.SalesChannelRepository;
import com.pgalindo.kata.order.importer.service.PriorityService;
import com.pgalindo.kata.order.importer.service.SalesChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SalesChannelServiceImpl implements SalesChannelService {

    private final SalesChannelRepository salesChannelRepository;

    @Autowired
    public SalesChannelServiceImpl(SalesChannelRepository salesChannelRepository) {
        this.salesChannelRepository = salesChannelRepository;
    }

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
}
