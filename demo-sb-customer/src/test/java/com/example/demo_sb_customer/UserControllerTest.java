package com.example.demo_sb_customer;


import java.util.List;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import com.example.demo_sb_customer.controller.impl.UserController;
import com.example.demo_sb_customer.dto.UserDTO;
import com.example.demo_sb_customer.dto.mapper.UserDTOMapper;
import com.example.demo_sb_customer.model.User;
import com.example.demo_sb_customer.service.UserService;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
  @MockBean
  UserService userService; // 10 method,mock 10 method

  @SpyBean
  private UserDTOMapper userDTOMapper; // 10 method ,real 10 method,still mock method

  @Test
  void testGetUsers() throws Exception{
    //Controller: convert List of Dto to List of DTO
    User john = User.builder().name("John").build();
    User mary = User.builder().name("Mary").build();
    

  }

}
