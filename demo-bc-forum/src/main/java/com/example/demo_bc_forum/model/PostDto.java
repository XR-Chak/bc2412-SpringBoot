package com.example.demo_bc_forum.model;

import lombok.Getter;

@Getter
public class PostDto {
  private Long userId;
  private Long id;
  private String title;
  private String body;
}
