package com.example.demo_sb_customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Bean;
import com.example.demo_sb_customer.entity.CustomerEntity;
import com.example.demo_sb_customer.repository.CustomerRepository;

@DataJpaTest //load repository related beans only
public class CustomerRepositoryTest {
  @Autowired
  private CustomerRepository customerRepository;

  @Test
  void testSave(){
    List<CustomerEntity>  beforeSave = customerRepository.findAll();
    assertEquals(0, beforeSave.size());
     CustomerEntity john = CustomerEntity.builder()
        .email("john@gmail.com").name("john").build();
       CustomerEntity customerEntity = customerRepository.save(john);

    assertEquals("john", customerEntity.getName());
    assertEquals("john@gmail.com", customerEntity.getEmail());
    assertEquals(1L, customerEntity.getId());


    CustomerEntity mary = CustomerEntity.builder()
        .email("mary@gmail.com").name("mary").build();
       CustomerEntity customerEntity2 = customerRepository.save(mary);

       assertEquals("mary", customerEntity2.getName());
       assertEquals("mary@gmail.com", customerEntity2.getEmail());
       assertEquals(2L, customerEntity2.getId());
}
}
