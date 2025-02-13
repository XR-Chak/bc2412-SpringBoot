package com.example.demo_sb_customer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Geo")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class geoEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "latitude")
  private String latitude;
  @Column(name = "longitude")
  private String longitude;
  @OneToOne
  @JoinColumn(name = "address_id")
  private AddressEntity addressEntity;
}
