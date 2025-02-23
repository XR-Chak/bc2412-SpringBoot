package com.example.demo_bc_forum.exception;

import com.example.demo_bc_forum.codewave.SysCode;

public class DBconnectError extends RuntimeException{
  private SysCode sysCode;

  public DBconnectError(SysCode sysCode){
    super(sysCode.getMessage());
    this.sysCode = sysCode;
  } 
}
