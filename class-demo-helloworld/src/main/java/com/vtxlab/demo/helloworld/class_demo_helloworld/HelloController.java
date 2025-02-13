package com.vtxlab.demo.helloworld.class_demo_helloworld;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import com.vtxlab.demo.helloworld.class_demo_helloworld.ShoppingMall.Cinema;
import com.vtxlab.demo.helloworld.class_demo_helloworld.ShoppingMall.Film;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


// Java Object -> JSON (Serialization)
// JSON -> Java Object (Deserialization)
@Controller //@GetMapping
@ResponseBody //Json
public class HelloController {


  //need getter to do getMapping!!!!!!!!!!!!
  @Getter
  @AllArgsConstructor
  public static class Cat{
    private String name;
    private int age;
  }


  @GetMapping("/greeting")
  public String hello(){
      return "Hello World!";
  }
  
  @GetMapping("/integers")
  public int[] getIntegers() {
    int[] ans = new int[]{1,2,3,4,5};
      return ans;
  }

  @GetMapping("/strings")
  public List<String> getStrings() {
    List<String> ans = Arrays.asList("Ray","asd","asdasdasd","asd");
      return ans;
  }
  
  @GetMapping("/cat")
  public Cat getCat() {
      Cat cat = new Cat("Hhaha", 1);
      return cat;
  }
  
  @GetMapping("/cats")
  public List<Cat> getCats(){
    List<Cat> cats = Arrays.asList(new Cat("asd", 0),new Cat("dsa", 12),new Cat("asdasd", 2));
    return cats;
  }

  @GetMapping(value = "/shoppingMall")
    public ShoppingMall getShopMall(){
      ShoppingMall.Film film1 = Film.builder()
      .name("film A")
      .releasedDate(LocalDate.of(2024, 10, 2))
      .build();
      ShoppingMall.Film film2 = Film.builder()
      .name("film B")
      .releasedDate(LocalDate.of(2022, 1, 2))
      .build();
      Cinema cinema = Cinema.builder()
      .name("ABC")
      .openedDate(LocalDate.of(2024, 8, 1))
      .releasedFilms(new Film[]{film1,film2})
      .build();
      return ShoppingMall.builder().name("K11")
      .area(19000).cinema(cinema)
      .shopCategory(Arrays.asList("Sport","Food","Clothing")).build();
    }
  

}
