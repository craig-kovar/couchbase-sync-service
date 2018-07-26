package com.cb.sync.entities;

import com.cb.sync.data.ProductInfo;
import com.cb.sync.data.HelpdeskInfo;
import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import lombok.*;
import org.springframework.data.couchbase.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Represents a Customer doc from Couchbase.
 *
 * @author Balaji
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Customer implements Serializable {

  private static final long serialVersionUID = 3072475211055736282L;
  protected static final String CUSTOMER_KEY_PREFIX = "customer::";

  @Id
//  @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
  private String key;

  @Setter(AccessLevel.NONE)
  @Field
  private Long id;

  @Field
  @NotNull
  private String name;

  @Field
  private String email;

  @Field
  private String company;

  @Field
  private String phoneNumber;

  @Field
  private String city;

  @Field
  private String zipCode;

  @Field
  private List<ProductInfo> productInfos;

  @Field
  private List<HelpdeskInfo> helpdeskInfo;

  public static String getKeyFor(Long id) {
    return CUSTOMER_KEY_PREFIX + id;
  }

  public void setId(Long id) {
    this.id = id;
    this.key = Customer.getKeyFor(id);
  }

}
