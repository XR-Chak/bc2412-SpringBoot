package com.example.demo_final_project.controller;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo_final_project.dto.Quote;
import com.example.demo_final_project.service.CrumbManager;
import com.example.demo_final_project.service.FinalProjectService;
import com.example.demo_final_project.service.YahooFinanceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@ResponseBody
public class FinalProjectController {
  @Autowired
  FinalProjectService finalProjectService;
  @Autowired
  CrumbManager crumbManager;
  @Autowired
  YahooFinanceManager yahooFinanceManager;
  @GetMapping("/crumb")
  public String getCrumb() {
      return this.crumbManager.getCrumb();
  }
  
  @GetMapping("/quote/{symbols}")
  public Quote getMethodName(@PathVariable String symbols) {
      return this.yahooFinanceManager.getQuote(symbols);
  }
  
}
