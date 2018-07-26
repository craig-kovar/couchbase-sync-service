package com.cb.sync.service;

import com.cb.sync.data.CustomerInfo;
import com.cb.sync.data.ProductCardsInfo;

import java.util.List;

public interface FTSService {

    void findByTextMatch(String searchText) throws Exception;

    ProductCardsInfo getRecommendation(String searchText);

    public List<CustomerInfo> getCustomers(String searchText);
}
