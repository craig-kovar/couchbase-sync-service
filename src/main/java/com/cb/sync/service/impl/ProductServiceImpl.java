package com.cb.sync.service.impl;

import com.cb.sync.datasource.product.repo.CustomerProductRepo;
import com.cb.sync.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired private CustomerProductRepo customerProductRepo;
}
