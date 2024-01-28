package com.pgalindo.kata.order.importer.usecase.get.jobs;

import com.pgalindo.kata.order.importer.service.JobService;
import com.pgalindo.kata.order.importer.usecase.get.jobs.response.JobFieldModel;
import com.pgalindo.kata.order.importer.usecase.get.jobs.response.JobsModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JobsUseCase {

    private final JobService jobService;

    public JobsModel getJobs() {
        List<JobFieldModel> allImportJobs = jobService.findAllImports()
                .stream()
                .map(job -> new JobFieldModel(job.getStatus().name(), job.getCreatedAt().toString(), job.getUpdatedAt().toString()))
                .toList();

        List<JobFieldModel> allClearDatabase = jobService.findAllClearDatabase()
                .stream()
                .map(job -> new JobFieldModel(job.getStatus().name(), job.getCreatedAt().toString(), job.getUpdatedAt().toString()))
                .toList();

        return new JobsModel(allImportJobs, allClearDatabase);
    }
}
