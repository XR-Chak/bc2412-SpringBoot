package com.example.demo_bc_forum.repository;

import java.math.BigInteger;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo_bc_forum.model.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity,Long>  {
  @Modifying
  @Transactional
  @Query(value = "Update Posts set user_id =null where user_id = :userId",nativeQuery = true)
  public void updateAllpostByUserId(@Param("userId") BigInteger userId );

}

