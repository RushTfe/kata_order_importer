package com.pgalindo.kata.order.importer.service.impl;

import com.pgalindo.kata.order.importer.model.entity.Region;
import com.pgalindo.kata.order.importer.repository.RegionRepository;
import com.pgalindo.kata.order.importer.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    /**
     * <p>
     *     Finds and return any Region matching regionName.
     *     If it doesn't find one, it will create a new one and return it.
     * </p>
     * @param regionName - Region name to find one
     * @return - The matching Region
     */
    @Override
    public Region findPriorityOrCreate(String regionName) {
        Optional<Region> region = regionRepository.findByNameIgnoreCase(regionName);

        if (region.isPresent()) {
            return region.get();
        }
        Region newRegion = new Region();
        newRegion.setName(regionName);
        return regionRepository.save(newRegion);
    }

    @Override
    public void clearTable() {
        regionRepository.deleteRegions();
    }
}
