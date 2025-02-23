package com.example.demo_bc_forum.exception;

import com.example.demo_bc_forum.codewave.SysCode;

public class JsonApiException extends RuntimeException{
  private SysCode sysCode;
  public JsonApiException(SysCode sysCode){
    super(sysCode.getMessage());
    this.sysCode = sysCode;
  }

  public SysCode getSysCode(){
    return this.sysCode;
  }
}
