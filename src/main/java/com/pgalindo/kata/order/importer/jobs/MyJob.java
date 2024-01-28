package com.pgalindo.kata.order.importer.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job {
    
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // Lógica de la tarea programada
        System.out.println("Ejecutando tarea programada...");
    }
}