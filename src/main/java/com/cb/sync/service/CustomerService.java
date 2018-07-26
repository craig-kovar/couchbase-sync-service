package com.cb.sync.service;

import com.cb.sync.entities.Customer;

import javax.validation.Valid;
import java.util.List;

/**
 * Interface for the Customer Service Implementation
 *
 * @author Balaji
 */
public interface CustomerService {

  Customer save(@Valid Customer customer);

  Customer findById(Long id);

  Customer create(@Valid Customer customer);

  void delete(Long id);

  Boolean exists(Long id);

  Customer findByEmail(String email);

  List<Customer> findAll();
}
