package com.cb.sync.datasource.product.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "zendesk")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Helpdesk implements Serializable {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "zendesk_id")
  private Integer id;

  @Column(name = "customer_email")
  private String customerEmail;

  private String question;
}
