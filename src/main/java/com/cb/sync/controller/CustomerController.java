package com.cb.sync.controller;

import java.util.List;
import javax.validation.Valid;

import com.cb.sync.entities.Customer;
import com.cb.sync.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


/**
 * Customer REST Controller
 * 
 * @author Balaji
 *
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

  protected static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

  @Autowired
  private CustomerService customerService;

  @GetMapping("/{customerId}")
  @ResponseStatus(HttpStatus.OK)
  public Customer findById(@PathVariable Long customerId) {
    logger.info("Find customer by id: {}", customerId);
    return customerService.findById(customerId);
  }

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public Customer create(@Valid @RequestBody Customer customer) {
    logger.info("Create customer");
    return customerService.create(customer);
  }

  @DeleteMapping("/{userId}")
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable Long userId) {
    logger.info("Delete customer: {}", userId);
    customerService.delete(userId);
  }

  @GetMapping("/{userId}/exists")
  @ResponseStatus(HttpStatus.OK)
  public Boolean exists(@PathVariable Long userId) {
    logger.info("Exists customer {}?", userId);
    return customerService.exists(userId);
  }

  @GetMapping("/find/email")
  @ResponseStatus(HttpStatus.OK)
  public Customer findByEmail(@RequestParam(value = "email") String email) {
    logger.info("Find customer by email: {}", email);
    return customerService.findByEmail(email);
  }

  @GetMapping("/find/all")
  @ResponseStatus(HttpStatus.OK)
  public List<Customer> findAll() {
    logger.info("Find all customers");
    return customerService.findAll();
  }

}
