package com.example.demo_bc_forum.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo_bc_forum.model.PostDto;
import com.example.demo_bc_forum.model.UserDto;
import com.example.demo_bc_forum.dto.PostDTO;
import com.example.demo_bc_forum.dto.UserCommentDTO;
import com.example.demo_bc_forum.dto.UserDTO;
import com.example.demo_bc_forum.model.CommentDto;
import com.example.demo_bc_forum.service.forumService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@ResponseBody
public class forumController {
  @Autowired
  private forumService forumService;

  @GetMapping("/forumUser")
  public List<UserDto> getforumUser() {
      return this.forumService.getUsers();
  }
  
  @GetMapping("/forumPost")
  public List<PostDto> getforumPost() {
      return this.forumService.getPosts();
  }

  @GetMapping("/forumComment")
  public List<CommentDto> getforumComment() {
      return this.forumService.getComments();
  }
  
  @GetMapping("/forumAll")
  public List<UserDTO> getMethodName() {
      return this.forumService.getUserDTOS();
  }
  
  @GetMapping("/forumUserComment/{id}")
  public UserCommentDTO getUserComments(@PathVariable Integer id) {
      
      return this.forumService.getUserComments(id);
  }
  
}
