package com.cb.sync.service.impl;

import com.cb.sync.data.CustomerInfo;
import com.cb.sync.data.ProductCardsInfo;
import com.cb.sync.service.FTSService;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.search.SearchQuery;
import com.couchbase.client.java.search.result.SearchQueryResult;
import com.couchbase.client.java.search.result.SearchQueryRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FTSServiceImpl implements FTSService {

  private static final Logger logger = LoggerFactory.getLogger(FTSServiceImpl.class);

  @Autowired private Bucket bucket;

  @Override
  public void findByTextMatch(String searchText) {
    SearchQueryResult result =
        bucket.query(
            new SearchQuery("cards", SearchQuery.match(searchText)));
    logger.info("****** total  hits := " + result.hits().size());
    for (SearchQueryRow hit : result.hits()) {
      logger.info(
          "****** score := "
              + hit.score()
              + " and content := "
              + bucket.get(hit.id()).content().get("description"));
    }
  }

  @Override
  public ProductCardsInfo getRecommendation(String searchText) {

    ProductCardsInfo productCardsInfo = new ProductCardsInfo();
    SearchQueryResult result =
        bucket.query(
            new SearchQuery("cards", SearchQuery.match(searchText)));
    logger.info(
        "****** total hits for search text : " + searchText + " := " + result.hits().size());
    for (SearchQueryRow hit : result.hits()) {
      logger.info(
          "****** score := "
              + hit.score()
              + " and content := "
              + bucket.get(hit.id()).content().get("description"));

      productCardsInfo.setName(bucket.get(hit.id()).content().get("productName").toString());
      productCardsInfo.setDescription(bucket.get(hit.id()).content().get("description").toString());
      productCardsInfo.setUrl(bucket.get(hit.id()).content().get("url").toString());
      productCardsInfo.setImage(bucket.get(hit.id()).content().get("image").toString());

      break;
    }
    return productCardsInfo;
  }

  @Override
  public List<CustomerInfo> getCustomers(String searchText) {

    List<CustomerInfo> customerInfos = new ArrayList<>();

    SearchQueryResult result =
            bucket.query(
                    new SearchQuery("customers", SearchQuery.match(searchText)));
    logger.info(
            "****** total hits for search text : " + searchText + " := " + result.hits().size());
    for (SearchQueryRow hit : result.hits()) {
      logger.info(
              "****** score := "
                      + hit.score()
                      + " and content := "
                      + bucket.get(hit.id()).content().get("email"));

      CustomerInfo customerInfo = new CustomerInfo();

      customerInfo.setName(bucket.get(hit.id()).content().get("name").toString());
      customerInfo.setEmail(bucket.get(hit.id()).content().get("email").toString());
      customerInfo.setCompany(bucket.get(hit.id()).content().get("company").toString());

      customerInfos.add(customerInfo);
    }
    return customerInfos;
  }
}
