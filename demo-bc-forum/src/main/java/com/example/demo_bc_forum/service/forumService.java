package com.example.demo_bc_forum.service;

import java.math.BigInteger;
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
import com.example.demo_bc_forum.model.entity.AddressEntity;
import com.example.demo_bc_forum.model.entity.CommentEntity;
import com.example.demo_bc_forum.model.entity.CompanyEntity;
import com.example.demo_bc_forum.model.entity.GeoEntity;
import com.example.demo_bc_forum.model.entity.PostEntity;
import com.example.demo_bc_forum.model.entity.UserEntity;
import com.example.demo_bc_forum.repository.AddressRepository;
import com.example.demo_bc_forum.repository.CommentRepository;
import com.example.demo_bc_forum.repository.CompanyRepository;
import com.example.demo_bc_forum.repository.GeoRepository;
import com.example.demo_bc_forum.repository.PostRepository;
import com.example.demo_bc_forum.repository.UserRepository;
import com.mysql.cj.conf.PropertyDefinitions.SslMode;
import jakarta.transaction.Transactional;
import com.example.demo_bc_forum.codewave.SysCode;
import com.example.demo_bc_forum.dto.CommentDTO;
import com.example.demo_bc_forum.dto.CommentDTOO;
import com.example.demo_bc_forum.dto.PostDTO;
import com.example.demo_bc_forum.dto.PostDTOO;
import com.example.demo_bc_forum.dto.UserCommentDTO;
import com.example.demo_bc_forum.dto.UserDTO;
import com.example.demo_bc_forum.dto.Mapper.CommentDTOMapper;
import com.example.demo_bc_forum.dto.Mapper.PostDTOMapper;
import com.example.demo_bc_forum.dto.Mapper.UserEntityMapper;
import com.example.demo_bc_forum.dto.UserDTO.Address;
import com.example.demo_bc_forum.dto.UserDTO.Company;
import com.example.demo_bc_forum.dto.UserDTO.Address.Geo;
import com.example.demo_bc_forum.exception.DBconnectError;
import com.example.demo_bc_forum.exception.InvalidInputException;
import com.example.demo_bc_forum.exception.JsonApiException;
import com.example.demo_bc_forum.exception.RestTemplateException;
import com.example.demo_bc_forum.exception.UserNotFoundException;
import com.example.demo_bc_forum.model.CommentDto;

@Service
public class forumService {
  @Autowired
  private AddressRepository addressRepository;
  @Autowired
  private CommentRepository commentRepository;
  @Autowired
  private CompanyRepository companyRepository;
  @Autowired
  private GeoRepository geoRepository;
  @Autowired
  private PostRepository postRepository;
  @Autowired
  private UserRepository userRepository;

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
       throw new JsonApiException(SysCode.API_Fail);
      }
    } 

    public List<PostDto> getPosts(){
      try {
         String url = "https://jsonplaceholder.typicode.com/posts";
      this.postdtos = Arrays.asList(this.restTemplate.getForObject(url, PostDto[].class));
      return this.postdtos;
      } catch (RuntimeException e) {
        throw new JsonApiException(SysCode.API_Fail);
       }
    }

    public List<CommentDto> getComments(){
      try {
        String url = "https://jsonplaceholder.typicode.com/comments";
      this.commentdtos = Arrays.asList(this.restTemplate.getForObject(url, CommentDto[].class));
      return this.commentdtos;
      } catch (RuntimeException e) {
        throw new JsonApiException(SysCode.API_Fail);
       }
      
    }
/////////////////////////////////////////////////////////Ex3
    @Transactional
    public void clearDatabase() {
      userRepository.deleteAll();
      companyRepository.deleteAll();
      addressRepository.deleteAll();
      geoRepository.deleteAll();
      postRepository.deleteAll();
      commentRepository.deleteAll();
      this.userRepository.resetAutoIncrement();
    }

    @Transactional
    public void saveAllToDB(){ //this method already run once when springboot start
        getUsers();
        getPosts();
        getComments();

        this.userdtos.stream().forEach(e->{
        GeoEntity geo = GeoEntity.builder().latitude(e.getAddress().getGeo().getLatitude())
        .longitude(e.getAddress().getGeo().getLongitude()).build();
        geo = this.geoRepository.save(geo);
 
        AddressEntity add = AddressEntity.builder().street(e.getAddress().getStreet()).suite(e.getAddress().getSuite())
        .city(e.getAddress().getCity()).zipcode(e.getAddress().getZipcode()).geo(geo).build();
        add = this.addressRepository.save(add);

        CompanyEntity com = CompanyEntity.builder().name(e.getCompany().getName()).catchPhrase(e.getCompany().getCatchPhrase())
        .bs(e.getCompany().getBs()).build();
        com = this.companyRepository.save(com);

        UserEntity user = UserEntity.builder().name(e.getName()).username(e.getUsername())
        .email(e.getEmail()).address(add).phone(e.getPhone()).website(e.getWebsite())
        .company(com).build();
        user = this.userRepository.save(user);

      });

      this.postdtos.stream().forEach(e->{
        PostEntity post = PostEntity.builder().userEntity(userRepository.findById(e.getUserId()).orElse(null)).title(e.getTitle())
        .body(e.getBody()).build();
        post = this.postRepository.save(post);
      });

      this.commentdtos.stream().forEach(e->{
        CommentEntity comment = CommentEntity.builder().postEntity(this.postRepository.findById(e.getPostId()).orElse(null)).name(e.getName())
        .email(e.getEmail()).body(e.getBody()).build();
        comment = this.commentRepository.save(comment);
      });
    }

      public List<UserEntity> getAllUser(){
        return this.userRepository.findAll();
      }

      public UserEntity getUser(Integer id){
        return this.userRepository.findById(id.longValue()).orElse(null);
      }

      public List<PostDto> findAllPosts(){
        List<PostEntity> posts =this.postRepository.findAll();
        List<PostDto> postDtos = posts.stream().map(e->{
          return PostDto.builder().userId(e.getUserEntity().getId()).id(e.getId()).title(e.getTitle())
          .body(e.getBody()).build();
        }).collect(Collectors.toList());
        return postDtos;
      }

      public PostDto addPost(Integer userId,PostDto postDto){
        UserEntity userEntity = this.userRepository.findById(userId.longValue()).orElse(null);
        PostEntity postEntity = PostEntity.builder().userEntity(userEntity).title(postDto.getTitle())
        .body(postDto.getBody()).build();

        PostEntity addedPost = this.postRepository.save(postEntity);
        return PostDto.builder().userId(addedPost.getUserEntity().getId()).id(addedPost.getId())
        .title(addedPost.getTitle()).body(addedPost.getBody()).build();
      }

      public List<PostEntity> deletePost(Integer postId){
        this.commentRepository.setpostIdNULL(postId.longValue());
        this.postRepository.deleteById(postId.longValue());
        return this.postRepository.findAll();
      }

      public CommentEntity patchComment(Integer id, String body){
        this.commentRepository.patchComment(id.longValue(), body);
        CommentEntity patchedComment = this.commentRepository.findById(id.longValue()).orElse(null);
        return patchedComment;
      }

      public List<UserEntity> updateUser(Integer id,UserDto userDto){
        if(this.userRepository.findById(id.longValue()).isPresent()){
          this.postRepository.updateAllpostByUserId(BigInteger.valueOf(id));
          this.userRepository.deleteById(id.longValue());

         GeoEntity geo = GeoEntity.builder().latitude(userDto.getAddress().getGeo().getLatitude())
          .longitude(userDto.getAddress().getGeo().getLongitude()).build();
         geo = this.geoRepository.save(geo);
          AddressEntity add = AddressEntity.builder().street(userDto.getAddress().getStreet())
          .suite(userDto.getAddress().getSuite()).city(userDto.getAddress().getCity()).zipcode(userDto.getAddress().getZipcode())
          .geo(geo).build();
          add = this.addressRepository.save(add);
          CompanyEntity com = CompanyEntity.builder().name(userDto.getCompany().getName()).catchPhrase(userDto.getCompany().getCatchPhrase())
          .bs(userDto.getCompany().getBs()).build();
          com = this.companyRepository.save(com);
          UserEntity userEntity = UserEntity.builder().name(userDto.getName()).username(userDto.getUsername())
          .email(userDto.getEmail()).address(add).phone(userDto.getPhone()).website(userDto.getWebsite())
          .company(com).build();
          this.userRepository.save(userEntity);
          return this.userRepository.findAll();
        }
        return null;
      }

//////////////////////////////////////////////////^^^^ex3^^^////////////////////////////////////////////////////////////////    
    
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
