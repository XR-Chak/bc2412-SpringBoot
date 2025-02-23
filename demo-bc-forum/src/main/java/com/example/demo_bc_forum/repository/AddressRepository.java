package com.example.demo_bc_forum.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo_bc_forum.model.entity.AddressEntity;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity,Long>{
  
}
