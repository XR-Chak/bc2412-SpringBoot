package com.example.demo_bc_forum.model;

import lombok.Getter;

@Getter
public class ErrorResult {
  private Integer code;
  private String message;

  public ErrorResult(Integer code,String message){
    this.code= code;
    this.message = message;
  }
}
