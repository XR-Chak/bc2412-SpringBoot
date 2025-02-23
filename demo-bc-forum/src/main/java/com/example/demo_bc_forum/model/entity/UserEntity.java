package com.example.demo_bc_forum.model.entity;
import jakarta.persistence.CascadeType;
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
@Table(name = "Users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "name")
  private String name;
  @Column(name = "username")
  private String username;
  @Column(name = "email")
  private String email;
  
  @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
  @JoinColumn(name = "address_id",referencedColumnName = "id")
  private AddressEntity address;
  
  @Column(name = "phone")
  private String phone;

  @Column(name = "website")
  private String website;

  @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
  @JoinColumn(name = "company_id", referencedColumnName = "id")
  private CompanyEntity company;
  
} 
