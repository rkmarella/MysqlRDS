package com.example.mySQL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@ComponentScan( basePackages = {"com.example.mySQL.controller"+"com.example.mySQL.service"})
//@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})

public class  MySqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySqlApplication.class, args);
	}

	@Bean
	public   RestTemplate getRestTemplate(){
		return  new RestTemplate();
	}
}
