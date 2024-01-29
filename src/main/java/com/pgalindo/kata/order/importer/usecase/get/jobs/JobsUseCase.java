package com.pgalindo.kata.order.importer.usecase.get.jobs;

import com.pgalindo.kata.order.importer.model.entity.AbstractJobEntity;
import com.pgalindo.kata.order.importer.model.entity.ImportJobEntity;
import com.pgalindo.kata.order.importer.service.JobService;
import com.pgalindo.kata.order.importer.usecase.get.jobs.response.JobFieldModel;
import com.pgalindo.kata.order.importer.usecase.get.jobs.response.JobsModel;
import com.pgalindo.kata.order.importer.utils.TimeUtils;
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
                .map(this::generateJobFieldModel)
                .toList();

        List<JobFieldModel> allClearDatabase = jobService.findAllClearDatabase()
                .stream()
                .map(this::generateJobFieldModel)
                .toList();

        return new JobsModel(allImportJobs, allClearDatabase);
    }

    private JobFieldModel generateJobFieldModel(AbstractJobEntity job) {
        return new JobFieldModel(job.getStatus().name(),
                TimeUtils.localDateTimeToString(job.getCreatedAt()),
                TimeUtils.localDateTimeToString(job.getUpdatedAt()));
    }
}
