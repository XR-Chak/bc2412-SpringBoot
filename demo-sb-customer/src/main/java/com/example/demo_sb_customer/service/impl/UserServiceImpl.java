package com.example.demo_sb_customer.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.demo_sb_customer.entity.AddressEntity;
import com.example.demo_sb_customer.entity.CompanyEntity;
import com.example.demo_sb_customer.entity.UserEntity;
import com.example.demo_sb_customer.entity.geoEntity;
import com.example.demo_sb_customer.entity.mapper.UserEntityMapper;
import com.example.demo_sb_customer.model.User;
import com.example.demo_sb_customer.repository.AddressRepository;
import com.example.demo_sb_customer.repository.CompanyRepository;
import com.example.demo_sb_customer.repository.GeoRepository;
import com.example.demo_sb_customer.repository.UserRepository;
import com.example.demo_sb_customer.service.UserService;


@Service 
public class UserServiceImpl implements UserService {
    
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private GeoRepository geoRepository;

    @Autowired
    private UserEntityMapper userEntityMapper;
    @Override
    public List<User> getUser(){
      String url = "https://jsonplaceholder.typicode.com/users";
      
      this.geoRepository.deleteAll();
      this.addressRepository.deleteAll();
      this.companyRepository.deleteAll();
      this.userRepository.deleteAll();
      
      List<User> userDtos = Arrays.asList(this.restTemplate
      .getForObject(url, User[].class));
      //save User List to DB
      userDtos.stream().forEach(e->{
        // UserEntity user = UserEntity.builder()
        // .name(e.getName()).email(e.getEmail())
        // .phone(e.getPhone()).website(e.getWebsite()).build();
        
        // AddressEntity address = AddressEntity.builder().street(e.getAddress().getStreet())
        // .suite(e.getAddress().getSuite()).city(e.getAddress().getCity())
        // .zipcode(e.getAddress().getZipcode()).userEntity(user).build();

        // geoEntity geo = geoEntity.builder() 
        // .latitude(e.getAddress().getGeo().getLatitude())
        // .longitude(e.getAddress().getGeo().getLongitude()).addressEntity(address).build();

        // CompanyEntity company = CompanyEntity.builder().name(e.getCompany()
        // .getName()).catchPhrase(e.getCompany().getCatchPhrase()).bs(e.getCompany().getBs())
        // .userEntity(user).build();
        //clear db
       
        
        
        UserEntity ue = this.userEntityMapper.map(e);
        this.userRepository.save(ue);
        AddressEntity ae = this.userEntityMapper.map(e.getAddress());
        ae.setUserEntity(ue);
        geoEntity ge = this.userEntityMapper.map(e.getAddress().getGeo());
        ge.setAddressEntity(ae);
        CompanyEntity ce = this.userEntityMapper.map(e.getCompany());
        ce.setUserEntity(ue);
        this.addressRepository.save(ae);
        this.geoRepository.save(ge);
        this.companyRepository.save(ce);
      });
      return userDtos;

    }

}
