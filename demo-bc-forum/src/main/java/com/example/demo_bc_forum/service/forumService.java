package com.example.demo_bc_forum.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.demo_bc_forum.model.PostDto;
import com.example.demo_bc_forum.model.UserDto;
import com.example.demo_bc_forum.dto.CommentDTO;
import com.example.demo_bc_forum.dto.CommentDTOO;
import com.example.demo_bc_forum.dto.PostDTO;
import com.example.demo_bc_forum.dto.PostDTOO;
import com.example.demo_bc_forum.dto.UserCommentDTO;
import com.example.demo_bc_forum.dto.UserDTO;
import com.example.demo_bc_forum.dto.Mapper.CommentDTOMapper;
import com.example.demo_bc_forum.dto.Mapper.PostDTOMapper;
import com.example.demo_bc_forum.dto.UserDTO.Address;
import com.example.demo_bc_forum.dto.UserDTO.Company;
import com.example.demo_bc_forum.dto.UserDTO.Address.Geo;
import com.example.demo_bc_forum.exception.InvalidInputException;
import com.example.demo_bc_forum.exception.RestTemplateException;
import com.example.demo_bc_forum.exception.UserNotFoundException;
import com.example.demo_bc_forum.model.CommentDto;

@Service
public class forumService {
   @Autowired
   private RestTemplate restTemplate;
    private List<UserDto> userdtos;
    private List<PostDto> postdtos;
    private List<CommentDto> commentdtos;
    public List<UserDto> getUsers(){
      try {
      String url = "https://jsonplaceholder.typicode.com/users";
      this.userdtos = Arrays.asList(this.restTemplate.getForObject(url, UserDto[].class));
      return this.userdtos;
      } catch (RuntimeException e) {
       throw new RestTemplateException("RestTemplate Error - JsonPlaceHolder");
      }
    } 

    public List<PostDto> getPosts(){
      try {
         String url = "https://jsonplaceholder.typicode.com/posts";
      this.postdtos = Arrays.asList(this.restTemplate.getForObject(url, PostDto[].class));
      return this.postdtos;
      } catch (RuntimeException e) {
        throw new RestTemplateException("RestTemplate Error - JsonPlaceHolder");
       }
     
    } 

    public List<CommentDto> getComments(){
      try {
        String url = "https://jsonplaceholder.typicode.com/comments";
      this.commentdtos = Arrays.asList(this.restTemplate.getForObject(url, CommentDto[].class));
      return this.commentdtos;
      } catch (RuntimeException e) {
        throw new RestTemplateException("RestTemplate Error - JsonPlaceHolder");
       }
      
    }

    public List<UserDTO> getUserDTOS(){
      getUsers();
      getPosts();
      getComments();
      Map<Long, List<CommentDto>> comments =  this.commentdtos.stream().collect(Collectors.groupingBy(e->e.getPostId()));
      Map<Long, List<CommentDTO>> comments2 = CommentDTOMapper.listmap(comments);
      List<PostDTO> postDTOs = new ArrayList<>();
      List<UserDTO> userDTOs = new ArrayList<>();
      for(int i=0;i<this.postdtos.size();i++){
        postDTOs.add(new PostDTO(this.postdtos.get(i).getUserId(),this.postdtos.get(i).getId()
         ,this.postdtos.get(i).getTitle(), this.postdtos.get(i).getBody(),comments2.get(i+1L)));
      }

      Map<Long, List<PostDTO>> posts = postDTOs.stream().collect(Collectors.groupingBy(e->e.getUserId()));
      Map<Long, List<PostDTOO>> posts2 = PostDTOMapper.listmap(posts);
      for(int i=0;i<this.userdtos.size();i++){
        Geo geo = Geo.builder().latitude(this.userdtos.get(i).getAddress().getGeo().getLatitude()).
        longitude(this.userdtos.get(i).getAddress().getGeo().getLongitude()).build();

        Address address = Address.builder().street(this.userdtos.get(i).getAddress().getStreet()).suite(this.userdtos.get(i).getAddress().getSuite()).
        city(this.userdtos.get(i).getAddress().getCity()).zipcode(this.userdtos.get(i).getAddress().getZipcode()).geo(geo).build();

        Company company = Company.builder().name(this.userdtos.get(i).getCompany().getName()).catchPhrase(this.userdtos.get(i).getCompany().getCatchPhrase())
        .bs(this.userdtos.get(i).getCompany().getBs()).build();
        
        userDTOs.add(new UserDTO(this.userdtos.get(i).getId(), this.userdtos.get(i).getName(), this.userdtos.get(i).getUsername()
        , this.userdtos.get(i).getEmail(),address ,this.userdtos.get(i).getPhone(), this.userdtos.get(i).getWebsite(), company, posts2.get(i+1L)));
      }
      return userDTOs;
    }

    public UserCommentDTO getUserComments(Integer id){
      if(id<=0){
        throw new InvalidInputException("Invalid input.");
      }
      try {
        UserDTO userDTO = getUserDTOS().get(id-1);
        List<CommentDTOO> comments = new ArrayList<>();
        for(PostDTOO pDTO:userDTO.getPosts()){
        for(CommentDTO cDTO:pDTO.getComments()){
          comments.add(CommentDTOO.builder().name(cDTO.getName())
          .email(cDTO.getEmail()).body(cDTO.getBody()).build());
        }
      }
      return UserCommentDTO.builder().id(userDTO.getId()).username(userDTO.getUsername())
      .comments(comments).build();
      } catch (RuntimeException e) {
        throw new UserNotFoundException("User not found!");
      }
      
    }
}
