package com.example.demo_sb_customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo_sb_customer.entity.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity,Long>{
  
}
