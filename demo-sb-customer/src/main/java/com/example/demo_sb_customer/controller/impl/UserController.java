package com.example.demo_sb_customer.controller.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo_sb_customer.dto.UserDTO;
import com.example.demo_sb_customer.dto.UserDTO.Address;
import com.example.demo_sb_customer.dto.UserDTO.Address.Geo;
import com.example.demo_sb_customer.model.User;
import com.example.demo_sb_customer.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class UserController {
  
  @Autowired
  UserService userService;

  @GetMapping("/users")
  public List<UserDTO> getUsers() {
    //List of UserDto ->List of UserDTO
    
    return this.userService.getUser().stream()
    .map(e->{
      Geo geo = new Geo(e.getAddress().getGeo().getLatitude()
      , e.getAddress().getGeo().getLongitude());
      return UserDTO.builder().id(e.getId())
      .name(e.getName()).email(e.getEmail())
      .phone(e.getPhone()).website(e.getWebsite())
      .address(new Address(e.getAddress().getStreet(), e.getAddress().getSuite(), e.getAddress().getCity()
      , e.getAddress().getZipcode(), geo)).build();
    }).collect(Collectors.toList());
  }
  
}
