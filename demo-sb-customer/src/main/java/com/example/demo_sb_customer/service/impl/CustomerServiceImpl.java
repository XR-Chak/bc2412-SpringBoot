package com.example.demo_sb_customer.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo_sb_customer.entity.CustomerEntity;
import com.example.demo_sb_customer.repository.CustomerRepository;
import com.example.demo_sb_customer.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public List<CustomerEntity> getCustomers() {
    return this.customerRepository.findAll();
  }

  @Override
  public CustomerEntity createCustomer(CustomerEntity customerEntity) {
    return this.customerRepository.save(customerEntity);
  }

  @Override
  public Optional<CustomerEntity> getCustomer(String name){
      return this.customerRepository.findByName(name);
  }

  @Override
  public List<CustomerEntity> getCustomerByNameByJpql(String name){
    return this.customerRepository.findByNameByJPQL(name);
  }

  @Override
  public List<CustomerEntity> getCustomerByNameByNQ(String name){
    return this.customerRepository.findByNameByNativeQuery(name);
  }

}