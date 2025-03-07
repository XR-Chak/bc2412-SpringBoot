package com.example.demo_final_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FinalProjectService {
  @Autowired
  private RestTemplate restTemplate;

}
