package com.example.demo_sb_restful.service;

import org.springframework.stereotype.Service;
import com.example.demo_sb_restful.model.Cat;
import com.example.demo_sb_restful.model.CatDatabase;

@Service //Bean
public class CatService {
  //stateless -->mean no attribute!!!!
  //stateless object -->can be a bean!!!!
  public boolean put(Cat cat){
    for(int i=0;i<CatDatabase.HOME.length;i++){
      if(CatDatabase.HOME[i] == null){
        CatDatabase.HOME[i] = cat;
        return true;
      }
    }
    return false;
  }
}
