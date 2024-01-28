package com.pgalindo.kata.order.importer.service.impl;

import com.pgalindo.kata.order.importer.model.entity.Priority;
import com.pgalindo.kata.order.importer.repository.PriorityRepository;
import com.pgalindo.kata.order.importer.service.PriorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriorityServiceImpl implements PriorityService {

    private final PriorityRepository priorityRepository;

    @Override
    public Priority findPriorityOrCreate(String priorityName) {
        Optional<Priority> priority = priorityRepository.findByNameIgnoreCase(priorityName);

        if (priority.isPresent()) {
            return priority.get();
        }
        Priority newPriority = new Priority();
        newPriority.setName(priorityName);
        return priorityRepository.save(newPriority);
    }

    @Override
    public void clearTable() {
        priorityRepository.deletePriorities();
    }
}
