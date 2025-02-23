package com.example.demo_bc_forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo_bc_forum.model.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity,Long>{
  @Modifying
  @Transactional
  @Query(value = "update comments set body = :body where id = :id",nativeQuery = true)
  public void patchComment(@Param("id") long id,@Param("body") String body);

  @Modifying
  @Transactional
  @Query(value = "Update comments set post_id = null where post_id = :postId",nativeQuery = true)
  public void setpostIdNULL(@Param("postId") long postId);

}
