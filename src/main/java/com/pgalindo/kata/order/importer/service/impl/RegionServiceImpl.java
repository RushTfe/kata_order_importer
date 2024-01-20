package com.pgalindo.kata.order.importer.service.impl;

import com.pgalindo.kata.order.importer.model.entity.Region;
import com.pgalindo.kata.order.importer.repository.RegionRepository;
import com.pgalindo.kata.order.importer.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    @Autowired
    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public Region findPriorityOrCreate(String regionName) {
        Optional<Region> region = regionRepository.findByName(regionName);

        if (region.isPresent()) {
            return region.get();
        }
        Region newRegion = new Region();
        newRegion.setName(regionName);
        return regionRepository.save(newRegion);
    }
}
