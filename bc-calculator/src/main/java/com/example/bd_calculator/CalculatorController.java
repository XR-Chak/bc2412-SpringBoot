package com.example.bd_calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@ResponseBody
@RestController
public class CalculatorController {
  @Autowired
  private CalculatorService calculatorService;


  @GetMapping(value="/operation{operation}")
  @ResponseStatus(HttpStatus.OK)
  public Result operate(@RequestParam String x,
  @RequestParam String y,@RequestParam String operation){
    try {
      x = x.replace("\"", "");
      y = y.replace("\"", "");
      operation = operation.replace("\"", "").toLowerCase();
      Double.parseDouble(x);
      Double.parseDouble(y);
      return this.calculatorService.
      operate(x,y,operation);
    } catch (Exception e) {
      new BusinessException("Invalid input");
    }
      return this.calculatorService.
      operate(x,y,operation);
  } 


}
