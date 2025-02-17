package com.example.demo_bc_forum.model;

import com.example.demo_bc_forum.dto.CommentDTO;
import lombok.Getter;

@Getter
public class CommentDto {
  private Long postId;
  private Long id;
  private String name;
  private String email;
  private String body;

  public CommentDTO toDTO(){
    return CommentDTO.builder().id(this.id).name(this.name).email(this.email)
    .body(this.body).build();
  }
}