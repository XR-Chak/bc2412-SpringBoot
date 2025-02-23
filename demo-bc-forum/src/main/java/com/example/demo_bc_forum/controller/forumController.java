package com.example.demo_bc_forum.controller;

import java.util.List;
import javax.xml.stream.events.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo_bc_forum.model.PostDto;
import com.example.demo_bc_forum.model.UserDto;
import com.example.demo_bc_forum.model.entity.CommentEntity;
import com.example.demo_bc_forum.model.entity.PostEntity;
import com.example.demo_bc_forum.model.entity.UserEntity;
import com.example.demo_bc_forum.codewave.ApiResp;
import com.example.demo_bc_forum.codewave.SysCode;
import com.example.demo_bc_forum.dto.PostDTO;
import com.example.demo_bc_forum.dto.UserCommentDTO;
import com.example.demo_bc_forum.dto.UserDTO;
import com.example.demo_bc_forum.exception.JsonApiException;
import com.example.demo_bc_forum.model.CommentDto;
import com.example.demo_bc_forum.service.forumService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@ResponseBody
public class forumController {
  @Autowired
  private forumService forumService;

  private final ObjectMapper objectMapper;
  public forumController(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

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
  
  @GetMapping("/getAllApi")///////////Do not use it!!!!!!
  public void getAllUsertest() {
      this.forumService.saveAllToDB();
  }
  
  @DeleteMapping("/formUserDelete")
  public void delAll(){
    this.forumService.clearDatabase();
  }

  @GetMapping("/getAllUser")
  public ApiResp<List<UserEntity>> getUsers() {
      List<UserEntity> allUser =this.forumService.getAllUser();
      return ApiResp.<List<UserEntity>>builder()
      .syscode(SysCode.OK).data(allUser).build();
  }

  @GetMapping("/getUserbyid")
  public ApiResp<UserEntity> getUserByID(@RequestParam Integer id) {
     UserEntity user = this.forumService.getUser(id);
    return ApiResp.<UserEntity>builder()
    .syscode(SysCode.OK).data(user).build();
  }
  
  @PutMapping("updateUser/{id}")
  public ApiResp<List<UserEntity>> updateUser(@PathVariable Integer id, @RequestBody UserDto userDto) {
      try {
        //  UserDto userDto = this.objectMapper.readValue(entity, UserDto.class);
        List<UserEntity> users = this.forumService.updateUser(id, userDto);
         return ApiResp.<List<UserEntity>>builder().syscode(SysCode.OK).data(users).build();
      } catch (Exception e) {
        throw new JsonApiException(SysCode.API_Fail);
      }
  }
  
  @GetMapping("getAllPosts")
  public ApiResp<List<PostDto>> getPosts() {
     List<PostDto> posts =  this.forumService.findAllPosts();
      return ApiResp.<List<PostDto>>builder().syscode(SysCode.OK).data(posts).build();
  }

  @PostMapping("addPost/{userId}")
  public ApiResp<PostDto> addPost(@PathVariable Integer userId ,@RequestBody PostDto postDto ) {
      //TODO: process POST request
     PostDto addedpost = this.forumService.addPost(userId,postDto);
      return ApiResp.<PostDto>builder().syscode(SysCode.OK).data(addedpost).build();
  }

  @PatchMapping("patchComment")
  public ApiResp<CommentEntity> patchComment(@RequestParam Integer id,@RequestBody String body) throws JsonProcessingException{
            String jsonString = body.replaceAll("^\"|\"$", "").replace("\\\"", "\"");
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            String text = jsonNode.get("body").asText();
            CommentEntity commentEntity = this.forumService.patchComment(id,text);
    return ApiResp.<CommentEntity>builder().syscode(SysCode.OK).data(commentEntity).build();
  }

  @DeleteMapping("deletePost/{id}")
  public ApiResp<List<PostEntity>> deletePost(@PathVariable Integer id){
    List<PostEntity> posts = this.forumService.deletePost(id);
    return ApiResp.<List<PostEntity>>builder().syscode(SysCode.OK).data(posts).build();
  }
}
