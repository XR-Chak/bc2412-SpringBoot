package com.example.demo_sb_customer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


// "mvn install" includes "mvn test"
// "mvn compile" ->ensure main code syntax alright
// "mvn test-compile" ensure test code syntax alright
// "mvn test" execute test ->SpringbootTest -> test once for the target testing env.

 
@SpringBootTest //simulate the actual Spring bean Cycle.
class DemoSbCustomerApplicationTests {

	// test all depandency between components
	@Test
	void contextLoads() {

	}

}
