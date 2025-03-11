package com.example.demo_final_project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CrumbManager {
  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private YahooCookieManager yahooCookieManager;

  //private static final String COOKIE = "B=12345abcde; GUC=AQEBCAFZ...";  // 你的 Cookie
  //private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36";
  public static String CRUMBKEY;
  public String getCrumb(){
    yahooCookieManager.setNewCookie();
    List<String> cookies = YahooCookieManager.cookies;
    String url = "https://query1.finance.yahoo.com/v1/test/getcrumb";
    
    HttpHeaders headers = new HttpHeaders();
    headers.set("User-Agent",
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
    System.out.println(cookies.toString());
    headers.set(HttpHeaders.COOKIE,String.join("", cookies));
    //headers.set(url, url);
    HttpEntity<String> entity = new HttpEntity<>(headers);

    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    CRUMBKEY = response.getBody();
    return response.getBody();
  }
  
}
