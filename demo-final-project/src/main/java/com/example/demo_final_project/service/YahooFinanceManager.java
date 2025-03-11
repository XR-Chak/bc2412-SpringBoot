package com.example.demo_final_project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.example.demo_final_project.dto.Quote;

@Component
public class YahooFinanceManager {
  @Autowired
  RestTemplate restTemplate;
  @Autowired
  CrumbManager crumbManager;

  
  //private static final String COOKIE = "B=12345abcde; GUC=AQEBCAFZ...";  // 你的 Cookie
  //private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36";
  public Quote getQuote(String symbols){
    this.crumbManager.getCrumb();
    String crumbKey = CrumbManager.CRUMBKEY;
    List<String> cookies = YahooCookieManager.cookies;
    String url = String
    .format("https://query1.finance.yahoo.com/v7/finance/quote?symbols=%s.HK&crumb=%s"
    , symbols,crumbKey);
    HttpHeaders headers = new HttpHeaders();
    headers.set("User-Agent",
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
    headers.set(HttpHeaders.COOKIE, String.join("", cookies));

    HttpEntity<String> entity = new HttpEntity<>(headers);

    ResponseEntity<Quote> response = restTemplate.exchange(url, HttpMethod.GET, entity, Quote.class);
    return response.getBody();
  }


}
