package com.example.demo_bc_forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo_bc_forum.model.entity.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity,Long>{
  
}
