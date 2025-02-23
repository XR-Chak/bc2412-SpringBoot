package com.example.demo_bc_forum.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.demo_bc_forum.service.forumService;

@Component
public class PreServerStartConfig implements CommandLineRunner {
  @Autowired
  forumService forumService;

  @Override
  public void run(String...args) throws Exception{
    this.forumService.clearDatabase();
    
    this.forumService.saveAllToDB();

  }
}
