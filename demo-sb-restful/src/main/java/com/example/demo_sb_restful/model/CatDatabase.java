package com.example.demo_sb_restful.model;

import java.util.Optional;

public class CatDatabase {
  public static final Cat[] HOME = new Cat[5];

  

  public static Optional<Cat> find(Long catId){
    for(Cat cat : HOME){
      if (cat.getId() == catId) {
        return Optional.of(cat);
      }
    }
    return Optional.empty();
  }

  public static Boolean delete(Long catId){
    for(int i=0;i<HOME.length;i++){
      if(HOME[i].getId()==catId){
        HOME[i]=null;
        return true;
      }
    }
    return false;
  }


  public static Boolean update(Long catId,Cat cat){
    for(int i=0;i<HOME.length;i++){
      if(HOME[i].getId()==catId){
        HOME[i] = cat;
        return true;
      }
    }
    return false;
  }

  //Dont create cat ,we should find the cat object
  //patch mean update part of the object value,similar to put
  public static Boolean patchName(Long catId,String catName){
    for(Cat cat : HOME){
      if (cat.getId() == catId) {
        cat.setName(catName);
        return true;
      }
    }
    return false;
  }
}
