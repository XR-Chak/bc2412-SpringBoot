package com.example.demo_bc_forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo_bc_forum.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
  @Modifying
  @Transactional
  @Query(value = "ALTER TABLE users AUTO_INCREMENT = 1",nativeQuery = true)
  public void resetAutoIncrement();
}
