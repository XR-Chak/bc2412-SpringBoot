package com.example.demo_bc_forum.dto;
import java.util.List;
import com.example.demo_bc_forum.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
  private Long userId;
  private Long id;
  private String title;
  private String body;
  private List<CommentDTO> comments;

  public PostDTOO toPostDTOO(){
    return PostDTOO.builder().id(this.id).title(this.title)
    .body(this.body).comments(this.comments).build();
  }
  
}
