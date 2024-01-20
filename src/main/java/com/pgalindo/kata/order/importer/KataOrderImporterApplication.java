package com.pgalindo.kata.order.importer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.pgalindo.kata.order.importer"}, scanBasePackageClasses = KataOrderImporterApplication.class)
public class KataOrderImporterApplication {

	public static void main(String[] args) {
		SpringApplication.run(KataOrderImporterApplication.class, args);
	}

}
