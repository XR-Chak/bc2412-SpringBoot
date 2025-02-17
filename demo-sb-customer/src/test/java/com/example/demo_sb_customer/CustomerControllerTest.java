package com.example.demo_sb_customer;

import static org.assertj.core.api.MatcherAssert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import java.util.Arrays;
import java.util.List;
import com.example.demo_sb_customer.codewave.ApiResp;
import com.example.demo_sb_customer.codewave.SysCode;
import com.example.demo_sb_customer.controller.impl.CustomerController;
import com.example.demo_sb_customer.entity.CustomerEntity;
import com.example.demo_sb_customer.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

// Every Test file is a testing env, with your target bean cycle.
// ! For Unit Test, we don't need service & repository layer (bean).
@WebMvcTest(controllers = CustomerController.class) // ! Context includes all web related beans ONLY.
class CustomerControllerTest {
  @MockBean
  private CustomerService customerService;

  // ! @WebMvcTest inject MockMvc Bean into context
  @Autowired
  private MockMvc mockMvc; // pretend Postman

  @Autowired
  private ObjectMapper objectMapper;
  @Test
  void testGetAllCustomers() throws Exception {
    // Mock behavior for the mock bean
    CustomerEntity customerEntity1 = CustomerEntity.builder()
        .email("test123@gmail.com").name("testname1").id(99L).build();
    CustomerEntity customerEntity2 = CustomerEntity.builder()
        .email("test234@gmail.com").name("testname2").id(999L).build();
    List<CustomerEntity> serviceResult =
        Arrays.asList(customerEntity1, customerEntity2);

    // Assume the behavior/result for the mock bean
    Mockito.when(customerService.getCustomers()).thenReturn(serviceResult);

    // Test
    // verify result
    ResultActions result = mockMvc.perform(get("/customers"));
    result.andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    // To check the data:
    // Approach 1
    result.andExpect(jsonPath("$.code").value("000000"))
        .andExpect(jsonPath("$.message").value("Success."))
        .andExpect(jsonPath("$.data[0].name").value("testname1"))
        .andExpect(jsonPath("$.data[0].email").value("test123@gmail.com"));

    // Approach 2
    String json = result.andReturn().getResponse().getContentAsString();

    ApiResp<List<CustomerEntity>> customerEntityArray = new ObjectMapper()
        .readValue(json, new TypeReference<ApiResp<List<CustomerEntity>>>() {});

    MatcherAssert.assertThat(customerEntityArray.getCode(), Matchers.is("000000"));
    MatcherAssert.assertThat(customerEntityArray.getMessage(), Matchers.is("Success."));

    List<CustomerEntity> customerEntities = customerEntityArray.getData();

    MatcherAssert.assertThat(customerEntities, Matchers.containsInAnyOrder( //
        Matchers.hasProperty("email", Matchers.equalTo("test123@gmail.com")), //
        Matchers.hasProperty("email", Matchers.equalTo("test234@gmail.com")) //
    ));
  }

  @Test
  void testCreateCustomer()throws Exception{
    CustomerEntity customerEntity3 = CustomerEntity.builder()
    .id(9999L).name("John").email("john123@gmail.com").build();
    
    Mockito.when(customerService.createCustomer(customerEntity3)).thenReturn(customerEntity3);
    
    ResultActions result = mockMvc.perform(post("/customer")
    .contentType(MediaType.APPLICATION_JSON)
    .content(objectMapper.writeValueAsString(customerEntity3)));

    result.andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    result.andExpect(jsonPath("$.code").value("000000"))
    .andExpect(jsonPath("$.message").value("Success."))
    .andExpect(jsonPath("$.data.name").value("John"));
  }
}
