package com.example.demo_bc_forum.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import com.example.demo_bc_forum.model.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
  private Long id;
  private String name;
  private String username;
  private String email;
  private Address address;
  private String phone;
  private String website;
  private Company company;
  private List<PostDTOO> posts;
  

  @Getter
  @Builder
  @Setter
  public static class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

   

    @Getter
    @Builder
    @Setter
    public static class Geo {
      @JsonProperty(value = "lat")
      private String latitude;
      @JsonProperty(value = "lng")
      private String longitude;
      
    }
  }

  @Getter
  @Builder
  @Setter
  public static class Company {
    private String name;
    private String catchPhrase;
    private String bs;
    
  }
}
