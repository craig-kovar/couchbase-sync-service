package com.cb.sync.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductInfo implements Serializable {

  private String productId;

  private String name;

  private String category;

  private String description;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd")
  private String startDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd")
  private String endDate;

  private int quantity;
}
