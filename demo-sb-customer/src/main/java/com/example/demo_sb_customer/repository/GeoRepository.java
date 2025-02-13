package com.example.demo_sb_customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo_sb_customer.entity.geoEntity;

public interface GeoRepository extends JpaRepository<geoEntity,Long> {
  
}
