package com.cb.sync.controller;

import javax.validation.Valid;

import com.cb.sync.datasource.product.entity.Product;
import com.cb.sync.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Product REST Controller
 * 
 * @author Balaji
 *
 */
@RestController
@RequestMapping("/products")
public class ProductController {

  protected static final Logger logger = LoggerFactory.getLogger(ProductController.class);

  @Autowired
  private ProductService productService;

  /*@GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Product findById(@PathVariable String id) {
    logger.info("Find product by id: {}", id);
    return productService.findById(id);
  }

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@Valid @RequestBody Product product) {
    logger.info("Create product");
    productService.create(product);
  }*/

}
