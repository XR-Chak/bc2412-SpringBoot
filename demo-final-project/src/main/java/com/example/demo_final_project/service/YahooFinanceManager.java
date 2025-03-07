package com.example.demo_final_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.example.demo_final_project.dto.Quote;

@Component
public class YahooFinanceManager {
  @Autowired
  RestTemplate restTemplate;
  @Autowired
  CrumbManager crumbManager;

  public Quote getQuote(String symbols){
    String crumbKey = CrumbManager.CRUMBKEY;
    String url = String
    .format("https://query1.finance.yahoo.com/v7/finance/quote?symbols=%s.HK&crumb=%s"
    , symbols,crumbKey);
    Quote quote = this.restTemplate.getForObject(url, Quote.class);
    return quote;
  }


}
