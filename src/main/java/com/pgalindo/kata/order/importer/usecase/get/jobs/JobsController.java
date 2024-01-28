package com.pgalindo.kata.order.importer.usecase.get.jobs;

import com.pgalindo.kata.order.importer.usecase.get.jobs.response.JobsModel;
import com.pgalindo.kata.order.importer.usecase.get.orders.response.SummariesModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class JobsController {

    private final JobsUseCase jobsUseCase;

    @GetMapping("/api/koi/jobs")
    String getOrders(Model model) {

        JobsModel jobsModel = jobsUseCase.getJobs();

        model.addAttribute("jobs", jobsModel);

        return "jobs";

    }
}
