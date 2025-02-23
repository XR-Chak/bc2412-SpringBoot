package com.example.demo_sb_customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo_sb_customer.service.CalculatorService;

@SpringBootTest
public class CalcultorServiceTest {
  @Autowired
  private CalculatorService calculatorService;

  @Test
  void testSum(){
    assertEquals(3,calculatorService.sum(1,2));
  }
}
