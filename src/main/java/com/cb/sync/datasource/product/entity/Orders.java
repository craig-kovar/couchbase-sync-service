package com.cb.sync.datasource.product.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Orders {

    @Id
    @Column(name = "order_id")
    private String orderId;

    @OneToOne
    @JoinColumn(name = "product_id_fk", nullable = false)
    private Product product;

    @NotBlank
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "purchase_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd")
    private Date purchaseDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expiration_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd")
    private Date expirationDate;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "customer_product_id_fk", nullable = false)
    private CustomerProductDetails customerProductDetails;

}
