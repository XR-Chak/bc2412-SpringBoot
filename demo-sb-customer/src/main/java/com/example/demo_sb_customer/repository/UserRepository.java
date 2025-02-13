package com.example.demo_sb_customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo_sb_customer.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long>{
  
}
