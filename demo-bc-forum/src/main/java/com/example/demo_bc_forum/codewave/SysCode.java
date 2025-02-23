package com.example.demo_bc_forum.codewave;

public enum SysCode {
  OK("000000","Success."),
  API_Fail("999998","Json PlaceHolder API unavaliable."),
  DB_Fail("999997","Database Connection Fail."),;

  private final String code;
  private final String message;

  private SysCode(String code,String message){
    this.code = code;
    this.message = message;
  }

  public String getCode(){
    return this.code;
  }

  public String getMessage(){
    return this.message;
  }
} 
