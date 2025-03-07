package com.example.demo_final_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CrumbManager {
  @Autowired
  private RestTemplate restTemplate;
  private static final String COOKIE = "B=12345abcde; GUC=AQEBCAFZ...";  // 你的 Cookie
  private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36";
  public static String CRUMBKEY;
  public String getCrumb(){
    String url = "https://query1.finance.yahoo.com/v1/test/getcrumb";
    
    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.USER_AGENT, USER_AGENT);
    headers.set(HttpHeaders.COOKIE, COOKIE);

    HttpEntity<String> entity = new HttpEntity<>(headers);

    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    CRUMBKEY = response.getBody();
    System.out.println(CRUMBKEY);
    return response.getBody();
  }
  
}
