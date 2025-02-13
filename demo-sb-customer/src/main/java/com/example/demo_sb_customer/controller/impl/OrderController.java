package com.example.demo_sb_customer.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo_sb_customer.codewave.ApiResp;
import com.example.demo_sb_customer.codewave.SysCode;
import com.example.demo_sb_customer.controller.OrderOperation;
import com.example.demo_sb_customer.entity.OrderEntity;
import com.example.demo_sb_customer.service.OrderService;

@RestController
public class OrderController implements OrderOperation {
  @Autowired
  private OrderService orderService;

  @Override
  public ApiResp<OrderEntity> createOrder(@RequestParam(value = "cid") Long customerId,
      @RequestBody OrderEntity orderEntity){
        OrderEntity serviceResult = this.orderService.createOrder(customerId, orderEntity);
        return ApiResp.<OrderEntity>builder()
        .syscode(SysCode.OK)
        .data(serviceResult).build();
      };


}
