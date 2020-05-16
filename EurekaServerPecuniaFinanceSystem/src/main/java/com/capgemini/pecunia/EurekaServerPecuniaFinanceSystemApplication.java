package com.capgemini.pecunia;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerPecuniaFinanceSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(EurekaServerPecuniaFinanceSystemApplication.class, args);
	}
}
