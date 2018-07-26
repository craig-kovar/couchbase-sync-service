package com.cb.sync.service;

import com.cb.sync.data.CustomerInfo;
import com.cb.sync.data.ProductCardsInfo;
import com.cb.sync.entities.Cards;

import javax.validation.Valid;
import java.util.List;

public interface ProductCardsService {

  Cards create(@Valid Cards cards);

  List<ProductCardsInfo> getProductRecommendation(String email);

  List<CustomerInfo> fetchCustomers(String searchText);

}
