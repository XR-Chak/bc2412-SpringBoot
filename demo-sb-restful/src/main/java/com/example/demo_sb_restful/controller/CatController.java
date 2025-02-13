package com.example.demo_sb_restful.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo_sb_restful.model.Cat;
import com.example.demo_sb_restful.model.CatDatabase;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.demo_sb_restful.service.CatService;

//! Restful api -> get/post/delete/put/patch
//Control single resource by get/post/delete/put/patch

//Controller ->the way to control Cat resource
//insert ,update,delete,select
@Controller
@ResponseBody
public class CatController {
  //cONTROLLER -> sERVICE->catdatabase
  //insert
  //Dependency injection (sPRING cORE CONCEPT)
  //Autowired: try to find an object which fits into CATSERVICE.(Before serever start complete)
  //if fails,server start fail

  //Field injection
  @Autowired
  private CatService CatService;
  @PostMapping(value = "/cat")
  public Cat createCat(@RequestBody Cat cat){
      if (CatService.put(cat)) {
        return cat;
      }
      return null;
  }

  //Get all cats
  @GetMapping(value = "/cats")
  public List<Cat> getCats(){
    return Arrays.asList(CatDatabase.HOME);
  }

  //get cat by id
  //http://localhost:8082/cat?id=1 ,variable name need to equal in the url!!!!
  //Deserializtion
  @GetMapping(value = "/cat")
  public Cat getCat(@RequestParam Long id){
    return CatDatabase.find(id).orElse(null);
  }


  @DeleteMapping(value = "/cat")
  public Boolean deleteCat(@RequestParam Long id){
    return CatDatabase.delete(id);
  }

  //HashMap.put() ->if exists,override,otherwise, create new
  @PutMapping(value = "/cat")
  public Boolean updateCat(@RequestParam Long id,@RequestBody Cat cat) {
    return CatDatabase.update(id, cat);
  }

  @PatchMapping(value = "/cat/name/{name}")
  public Boolean updateCat(@RequestParam Long id,@PathVariable String name) {
    return CatDatabase.patchName(id,name);
}


}
