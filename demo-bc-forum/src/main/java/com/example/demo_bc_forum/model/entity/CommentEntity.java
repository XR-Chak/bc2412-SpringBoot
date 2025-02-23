package com.example.demo_bc_forum.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Comments")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity {
  @ManyToOne
  @JoinColumn(name = "postId")
  private PostEntity postEntity;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String email;
  
  @Column(name = "body",length = 500)
  private String body;
}
