package com.example.demo_sb_customer.controller.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo_sb_customer.codewave.ApiResp;
import com.example.demo_sb_customer.codewave.SysCode;
import com.example.demo_sb_customer.controller.CustomerOperation;
import com.example.demo_sb_customer.entity.CustomerEntity;
import com.example.demo_sb_customer.service.CustomerService;

@RestController
public class CustomerController implements CustomerOperation {
  @Autowired
  private CustomerService customerService;

  @Override
  public ApiResp<List<CustomerEntity>> getCustomers() {
    List<CustomerEntity> customerEntities = this.customerService.getCustomers();
    return ApiResp.<List<CustomerEntity>>builder() //
        .syscode(SysCode.OK) //
        .data(customerEntities) //
        .build();
  }

  @Override
  public ApiResp<CustomerEntity> createCustomer(CustomerEntity customerEntity) {
    System.out.println("createCustomer: customerEntity=" + customerEntity);
    CustomerEntity serviceResult =
        this.customerService.createCustomer(customerEntity);
    return ApiResp.<CustomerEntity>builder() //
        .syscode(SysCode.OK) //
        .data(serviceResult) //
        .build();
  }
  
  @Override
  public ApiResp<List<CustomerEntity>> getCustomerByName1(@RequestParam String name){
    return ApiResp.<List<CustomerEntity>>builder().syscode(SysCode.OK)
    .data(this.customerService.getCustomerByNameByJpql(name)).build();
  }

  @Override
  public ApiResp<List<CustomerEntity>> getCustomerByName2(@RequestParam String name){
    return ApiResp.<List<CustomerEntity>>builder().syscode(SysCode.OK)
    .data(this.customerService.getCustomerByNameByNQ(name)).build();
  }
}