package com.example.demo_sb_restful.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
@Setter
public class Cat {
  private Long id; //Wrapper class(can return null!!!) for serialization/deserialization
  private String name;
  private int age;
}
