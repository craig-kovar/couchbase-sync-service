package com.cb.sync.datasource.product.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Product {

  @Id
  @Column(name = "product_id")
  private String productId;

  @NotBlank
  private String name;

  private String category;

  @NotBlank
  private String description;

}
