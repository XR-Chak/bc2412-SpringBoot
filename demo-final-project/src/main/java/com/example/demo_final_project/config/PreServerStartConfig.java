package com.example.demo_final_project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import com.example.demo_final_project.service.CrumbManager;

@DependsOn("restTemplate")
@Component
public class PreServerStartConfig implements CommandLineRunner {
  // @Autowired
  // CrumbManager crumbManager;

  @Override
  public void run(String...args) throws Exception{
    // this.crumbManager.getCrumb();
    // System.out.println("asdasdasdasd:::"+CrumbManager.CRUMBKEY);
  }
}
