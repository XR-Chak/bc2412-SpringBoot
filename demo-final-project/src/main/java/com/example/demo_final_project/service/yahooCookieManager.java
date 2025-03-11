package com.example.demo_final_project.service;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.CookieManager;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import com.example.demo_final_project.dto.Quote;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@Component
public class YahooCookieManager {
  @Autowired
  RestTemplate restTemplate;

  public static List<String> cookies = null;

  public void setNewCookie(){
    String url = "https://fc.yahoo.com";
    // 設定 request headers
    HttpHeaders headers = new HttpHeaders();
    headers.set("User-Agent", "Mozilla/5.0");

    HttpEntity<String> entity = new HttpEntity<>(headers);
    
    try {
      ResponseEntity<String> response =
          restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
      System.out.println("Response body: " + response.getBody());
      System.out.println("Response headers: " + response.getHeaders());
    } catch (HttpClientErrorException | HttpServerErrorException ex) {
      cookies = ex.getResponseHeaders().get(HttpHeaders.SET_COOKIE);
    }

  }
        
    
}


