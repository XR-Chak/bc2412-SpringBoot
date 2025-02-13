package com.example.demo_sb_restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication //@SpringBootConfiguaration + @EnableAutoConfiguration + @ComponentScan
// @ComponentScan ->search the whole project,check if any 
//@ComponentScan,@SERVICE,@Repository,@Configuration 
//@ComponentScan,@SERVICE,@Repository,@Configuration are a type of component
public class DemoSbRestfulApplication {


	public static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = 
		SpringApplication.run(DemoSbRestfulApplication.class, args);
	}

}
