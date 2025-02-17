package com.example.demo_sb_customer.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Customers")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class CustomerEntity {
  @Id // PK
  @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
  private Long id;
  @Column(name = "customer_name")
  private String name;
  @Column(name = "customer_email")
  private String email;
}
