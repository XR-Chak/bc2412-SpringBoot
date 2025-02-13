package com.example.demo_sb_restful.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.demo_sb_restful.DemoSbRestfulApplication;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class BeanController {
  @GetMapping(value = "/beans")
  public List<String> getBeans() {
    return Arrays.asList(DemoSbRestfulApplication.context.getBeanDefinitionNames());
  }
}
