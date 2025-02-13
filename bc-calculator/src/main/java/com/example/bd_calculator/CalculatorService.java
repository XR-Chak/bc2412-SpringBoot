package com.example.bd_calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService{

  public Result operate(String x,String y,String operation) throws BusinessException{
    try {
      Double.parseDouble(x);
      Double.parseDouble(y);
    } catch (Exception e) {
      throw new BusinessException("Invalid input");
    }
    Double ans = switch (operation) {
      case "add" -> sum((Double.parseDouble(x)), (Double.parseDouble(y)));
      case "sub" -> subtract((Double.parseDouble(x)), (Double.parseDouble(y)));
      case "mul" -> multiply((Double.parseDouble(x)), (Double.parseDouble(y)));
      case "div" -> divide((Double.parseDouble(x)), (Double.parseDouble(y)));
      default -> -999999999.0;
    };
    if(ans ==-999999999.0){
      throw new BusinessException("Invalid input");
    }
    return new Result(String.valueOf(x), String.valueOf(y), operation, String.valueOf(ans));
  }

  private Double sum(Double x, Double y) {
    return x + y;
  }

  private Double subtract(Double x, Double y) {
    return x - y;
  }

  private Double multiply(Double x, Double y) {
    return BigDecimal.valueOf(x).multiply(BigDecimal.valueOf(y)).doubleValue();
  }

  private Double divide(Double x, Double y) {
    if (y == 0.0)
      throw new BusinessException(
          "CalculatorService.divide() / zero." + "x=" + x + ",y=" + y);
    return BigDecimal.valueOf(x).
    divide(BigDecimal.valueOf(y),5,RoundingMode.HALF_UP).setScale(5, RoundingMode.HALF_UP).doubleValue();
  }
}
