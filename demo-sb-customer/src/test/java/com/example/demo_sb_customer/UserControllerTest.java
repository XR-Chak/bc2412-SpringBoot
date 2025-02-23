package com.example.demo_sb_customer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.apache.tomcat.util.http.parser.MediaType;
import org.assertj.core.util.Arrays;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.example.demo_sb_customer.controller.impl.UserController;
import com.example.demo_sb_customer.dto.UserDTO;
import com.example.demo_sb_customer.dto.mapper.UserDTOMapper;
import com.example.demo_sb_customer.model.User;
import com.example.demo_sb_customer.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
  @MockBean
  private UserService userService; // 10 method, mock 10 method

  @SpyBean
  private UserDTOMapper userDTOMapper; // 10 method, real 10 method, still mock method

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testGetUsers() throws Exception {
    // john
    User.Address.Geo johnGeo = User.Address.Geo.builder().build();
    User.Address johnAddress =
        User.Address.builder().geo(johnGeo).build();
    User.Company johnCompany = User.Company.builder().build();
    User john = User.builder().name("John").address(johnAddress)
        .company(johnCompany).build();

    // mary
    User.Address.Geo maryGeo = User.Address.Geo.builder().build();
    User.Address maryAddress =
        User.Address.builder().geo(maryGeo).build();
    User.Company maryCompany = User.Company.builder().build();
    User mary = User.builder().name("Mary").address(maryAddress)
        .company(maryCompany).build();

    List<User> userDtos = new ArrayList<>();
    userDtos.add(john);
    userDtos.add(mary);

    Mockito.when(userService.getUser()).thenReturn(userDtos);

    ResultActions result = mockMvc.perform(get("/jsonplaceholder/users"));
    result.andExpect(status().isOk())
        .andExpect(content().contentType(org.springframework.http.MediaType.APPLICATION_JSON));

    // To check the data:
    String json = result.andReturn().getResponse().getContentAsString();
    List<UserDTO> userDTOs = new ObjectMapper().readValue(json,
        new TypeReference<List<UserDTO>>() {});

    MatcherAssert.assertThat(userDTOs, Matchers.containsInAnyOrder( //
        Matchers.hasProperty("name", Matchers.equalTo("John")), //
        Matchers.hasProperty("name", Matchers.equalTo("Mary")) //
    ));
  }
}
