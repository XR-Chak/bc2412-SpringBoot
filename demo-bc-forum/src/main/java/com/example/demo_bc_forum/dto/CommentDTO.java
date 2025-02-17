package com.example.demo_bc_forum.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@Setter
@ToString
public class CommentDTO {
  private Long id;
  private String name;
  private String email;
  private String body;
}
