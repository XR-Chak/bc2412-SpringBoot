package com.example.demo_bc_forum.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTOO {
  private Long id;
  private String title;
  private String body;
  private List<CommentDTO> comments;
}
