package com.example.demo_final_project.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CorporateAction {
  private String header;
  private String message;
  private Meta meta;


  @Getter
  @Setter
  @Builder
  public static class Meta {
    private String eventType;
    private Long dateEpochMs;
    private String amount;

  }
}
