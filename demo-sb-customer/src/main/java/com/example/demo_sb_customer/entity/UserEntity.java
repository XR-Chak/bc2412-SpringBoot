package com.example.demo_sb_customer.entity;

import com.example.demo_sb_customer.model.User;
import com.example.demo_sb_customer.model.User.Address;
import com.example.demo_sb_customer.model.User.Company;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Users")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
  @Id
  @GeneratedValue(strategy =GenerationType.IDENTITY)
  private Long id;
  @Column(name = "name")
  private String name;
  @Column(name = "username")
  private String username;
  @Column(name ="email")
  private String email;
  @Column(name ="phone")
  private String phone;
  @Column(name = "website")
  private String website;

}
