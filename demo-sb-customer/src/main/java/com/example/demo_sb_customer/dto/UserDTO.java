package com.example.demo_sb_customer.dto;

import com.example.demo_sb_customer.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//Different numbers of field for the API

//This DTO is for serialization (obj ->json)

@Getter
@Builder
@AllArgsConstructor
public class UserDTO {
  private Long id;
  private String name;
  private String email;
  private Address address;
  private String phone;
  private String website;

  

  @Getter
  @Builder
  @AllArgsConstructor
  public static class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Geo {
      @JsonProperty(value = "lat")
      private String latitude;
      @JsonProperty(value = "lng")
      private String longitude;
      
    }
  }
  public static void main(String[] args) {
    //User userDto2 = new User();
  }
}
