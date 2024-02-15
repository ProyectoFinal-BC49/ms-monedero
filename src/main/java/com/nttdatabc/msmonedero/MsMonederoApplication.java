package com.nttdatabc.msmonedero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
public class MsMonederoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsMonederoApplication.class, args);
	}

}
