package com.cb.sync.datasource.product.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "customer_product_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString(exclude = "orders")
public class CustomerProductDetails {

    @Id
    private String id;

    @NotBlank
    @Column(name = "customer_email")
    private String customerEmail;

    @OneToMany(mappedBy = "customerProductDetails")
    private List<Orders> orders;

}
