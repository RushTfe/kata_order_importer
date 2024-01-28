package com.pgalindo.kata.order.importer.jobs;

import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderImporterJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(OrderImporterJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("Executing Order import job.");

        // TODO Buscar en la tabla de jobs aquí

        // TODO Ejecutar el caso de uso aquí.

        logger.info("Executed Order import job.");
    }
}