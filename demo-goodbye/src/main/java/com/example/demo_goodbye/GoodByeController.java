package com.example.demo_goodbye;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodByeController {
  @GetMapping(value = "/ipad/goodbye")
  public String goodbye(){
    return "GoodBye!";
  }
}
