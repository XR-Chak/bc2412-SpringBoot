package com.example.demo_final_project.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import com.example.demo_final_project.service.CrumbManager;

@DependsOn("restTemplate")
public class PreServerStartConfig implements CommandLineRunner {
  @Override
  public void run(String...args) throws Exception{
    CrumbManager crumbManager = new CrumbManager();
    crumbManager.getCrumb();
    System.out.println(CrumbManager.CRUMBKEY);
  }
}
