package com.example.bd_calculator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResult {
  private int code = 9;
  private String message;
}
