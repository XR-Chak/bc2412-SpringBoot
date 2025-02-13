package com.example.demo_sb_customer.entity.mapper;

import org.springframework.stereotype.Component;
import com.example.demo_sb_customer.entity.AddressEntity;
import com.example.demo_sb_customer.entity.CompanyEntity;
import com.example.demo_sb_customer.entity.UserEntity;
import com.example.demo_sb_customer.entity.geoEntity;
import com.example.demo_sb_customer.model.User;

@Component
public class UserEntityMapper {
  public UserEntity map(User dto){
    return UserEntity.builder().email(dto.getEmail())
    .name(dto.getName()).username(dto.getUsername())
    .email(dto.getEmail()).phone(dto.getPhone()).website(dto.getWebsite())
    .build();
}

  public AddressEntity map(User.Address add){
    return AddressEntity.builder().street(add.getStreet())
        .suite(add.getSuite()).city(add.getCity())
        .zipcode(add.getZipcode()).build();//need a setter to set the userEntity
  }

  public CompanyEntity map(User.Company com){
    return CompanyEntity.builder().name(com.getName()).catchPhrase(com.getCatchPhrase())
    .bs(com.getBs()).build();//need a setter to set the  userEntity
  }

  public geoEntity map(User.Address.Geo geo){
    return geoEntity.builder()
    .latitude(geo.getLatitude())
    .longitude(geo.getLongitude()).build();//need a setter to set the  addressEntity
  }
}
