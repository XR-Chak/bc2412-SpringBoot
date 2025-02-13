package com.vtxlab.demo.helloworld.class_demo_helloworld;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ShoppingMall {
  private String name;
  private int area;
  private Cinema cinema;
  private List<String> shopCategory;

  @Builder
  @Getter
  public static class Cinema{
    private String name;
    private LocalDate openedDate;
    private Film[] releasedFilms;
  }
  @Builder
  @Getter
  public static class Film{
    private String name;
    private LocalDate releasedDate;
  }

  

  public static void main(String[] args) {
    
  }
}
