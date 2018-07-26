package com.cb.sync.entities;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import lombok.*;
import org.springframework.data.couchbase.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Cards implements Serializable {
  protected static final String CARDS_KEY_PREFIX = "cards::";

  private static final long serialVersionUID = 3072475211055736282L;

  @Id
  //  @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
  private String key;

  @Setter(AccessLevel.NONE)
  @Field
  private Long id;

  @Field
  @NotNull
  private String productName;

  @Field
  private String description;

  @Field
  private String url;

  @Field
  private String image;

  public static String getKeyFor(Long id) {
    return CARDS_KEY_PREFIX + id;
  }

  public void setId(Long id) {
    this.id = id;
    this.key = Cards.getKeyFor(id);
  }
}
