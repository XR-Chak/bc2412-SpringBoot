package com.example.demo_bc_forum.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostDto {
  private Long userId;
  private Long id;
  private String title;
  private String body;
}
