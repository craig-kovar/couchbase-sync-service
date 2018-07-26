package com.cb.sync.datasource.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(schema = "cb_demo", name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String name;

  @NotBlank
  private String email;

  @NotBlank
  private String company;

  @NotBlank
  private String phoneNumber;

  @NotBlank
  private String city;

  @NotBlank
  private String zipCode;

}
