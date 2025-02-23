package com.example.demo_bc_forum.dto.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.demo_bc_forum.model.UserDto;
import com.example.demo_bc_forum.model.entity.AddressEntity;
import com.example.demo_bc_forum.model.entity.CompanyEntity;
import com.example.demo_bc_forum.model.entity.GeoEntity;
import com.example.demo_bc_forum.model.entity.UserEntity;
import com.example.demo_bc_forum.repository.AddressRepository;
import com.example.demo_bc_forum.repository.CompanyRepository;
import com.example.demo_bc_forum.repository.GeoRepository;
import com.example.demo_bc_forum.repository.UserRepository;

@Component
public class UserEntityMapper {
  @Autowired
  private static CompanyRepository companyRepository;
  @Autowired
  private static AddressRepository addressRepository;
  // @Autowired
  // private static GeoRepository geoRepository;
  @Autowired
  private static UserRepository userRepository;

  public static CompanyEntity map(UserDto.Company com){
     CompanyEntity COM = CompanyEntity.builder().bs(com.getBs())
    .catchPhrase(com.getCatchPhrase()).name(com.getName()).build();
    companyRepository.save(COM);
    return COM;


  }

  public static GeoEntity map(UserDto.Address.Geo geo){
    GeoEntity GEO = GeoEntity.builder()
    .latitude(geo.getLatitude()).longitude(geo.getLongitude()).build();
    System.out.println("GEO----" + GEO);
    
  //   if (geoRepository == null) {
  //     throw new IllegalStateException("geoRepository is not initialized");
  // }

    // GEO = geoRepository.save(GEO);
    return GEO;
  }

  public static AddressEntity map(UserDto.Address add){
    GeoEntity geo = UserEntityMapper.map(add.getGeo());
    AddressEntity ADD = AddressEntity.builder().street(add.getStreet()).suite(add.getSuite())
    .city(add.getCity()).zipcode(add.getZipcode()).geo(geo).build();
    addressRepository.save(ADD);
    return ADD;
  }

  public static UserEntity map(UserDto user){
    AddressEntity add = UserEntityMapper.map(user.getAddress());
    CompanyEntity com = UserEntityMapper.map(user.getCompany());
    UserEntity USER = UserEntity.builder().name(user.getName()).username(user.getUsername()).email(user.getEmail())
    .address(add).phone(user.getPhone()).website(user.getWebsite()).company(com).build();
    userRepository.save(USER);
    return USER;
  }

}
