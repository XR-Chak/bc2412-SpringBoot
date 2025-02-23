package com.example.demo_bc_forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo_bc_forum.model.entity.GeoEntity;

public interface GeoRepository extends JpaRepository<GeoEntity,Long> {
  
}
