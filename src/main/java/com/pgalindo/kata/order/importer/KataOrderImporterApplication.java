package com.pgalindo.kata.order.importer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.pgalindo.kata.order.importer"}, scanBasePackageClasses = KataOrderImporterApplication.class)
@EnableFeignClients
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class KataOrderImporterApplication {

    public static void main(String[] args) {
        SpringApplication.run(KataOrderImporterApplication.class, args);
    }

}
