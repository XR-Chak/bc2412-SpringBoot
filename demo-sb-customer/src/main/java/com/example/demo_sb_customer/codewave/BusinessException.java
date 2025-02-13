package com.example.demo_sb_customer.codewave;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class BusinessException extends RuntimeException {
  private SysCode syscode;

  public static BusinessException of(SysCode sysCode) {
    return new BusinessException(sysCode);
  }

  private BusinessException(SysCode sysCode) {
    super(sysCode.getMessage());
    this.syscode = sysCode;
  }

  public SysCode getSysCode() {
    return this.syscode;
  }
}
