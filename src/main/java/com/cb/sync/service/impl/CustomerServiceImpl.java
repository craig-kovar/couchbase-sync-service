package com.cb.sync.service.impl;

import com.cb.sync.entities.Customer;
import com.cb.sync.exception.ApiException;
import com.cb.sync.exception.ErrorType;
import com.cb.sync.repo.CustomerRepo;
import com.cb.sync.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Service that manages the valid operations over the customer repository.
 *
 * @author Balaji
 */
@Service
public class CustomerServiceImpl implements CustomerService {

  @Autowired private CustomerRepo customerRepo;

  @Override
  public Customer findByEmail(String email) {
    return customerRepo.findByEmail(email);
  }

  @Override
  public Customer findById(Long id) {
    final Optional<Customer> customer = customerRepo.findById(id);
    return customer.orElseThrow(() -> new ApiException(ErrorType.CUSTOMER_NOT_FOUND));
  }

  @Override
  public Customer create(@Valid Customer customer) {
    // check if the customer already exist
    final Optional<Customer> customerObj = Optional.ofNullable(this.findByEmail(customer.getEmail()));
    if (customerObj.isPresent()) {
      throw new ApiException(ErrorType.CUSTOMER_ALREADY_EXISTS);
    }
    return customerRepo.save(customer);
  }

  @Override
  public void delete(Long id) {
    // The customer should exist
    this.findById(id);
    customerRepo.deleteById(id);
  }

  @Override
  public Customer save(@Valid Customer customer) {
    return customerRepo.save(customer);
  }

  @Override
  public Boolean exists(Long id) {
    return customerRepo.existsById(id);
  }

  @Override
  public List<Customer> findAll() {
    return customerRepo.findAllCustomers();
  }
}
