package com.cb.sync.service.impl;

import com.cb.sync.constants.CBConstants;
import com.cb.sync.data.Conversations;
import com.cb.sync.data.CustomerInfo;
import com.cb.sync.data.HelpdeskInfo;
import com.cb.sync.data.ProductCardsInfo;
import com.cb.sync.entities.Cards;
import com.cb.sync.entities.Customer;
import com.cb.sync.repo.CardsCounterRepo;
import com.cb.sync.repo.CardsRepo;
import com.cb.sync.service.CustomerService;
import com.cb.sync.service.FTSService;
import com.cb.sync.service.ProductCardsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCardsServiceImpl implements ProductCardsService, CBConstants {

  private static final Logger logger = LoggerFactory.getLogger(ProductCardsServiceImpl.class);

  @Autowired private CustomerService customerService;

  @Autowired private FTSService ftsService;

  @Autowired private CardsRepo cardsRepo;

  @Autowired private CardsCounterRepo cardsCounterRepo;

  @Override
  public Cards create(@Valid Cards cards) {
    // check if the customer already exist
    cards.setId(cardsCounterRepo.counter());
    return cardsRepo.save(cards);
  }

  @Override
  public List<ProductCardsInfo> getProductRecommendation(String email) {

    List<ProductCardsInfo> productCards = new ArrayList<>();

    if (SUPPORT.equalsIgnoreCase(email)) {
      List<Cards> cards = cardsRepo.findAllCards();
      if (cards != null && !cards.isEmpty()) {
        cards
            .stream()
            .forEach(
                card -> {
                  ProductCardsInfo productCardsInfo = new ProductCardsInfo();
                  productCardsInfo.setName(card.getProductName());
                  productCardsInfo.setDescription(card.getDescription());
                  productCardsInfo.setImage(card.getImage());
                  productCardsInfo.setUrl(card.getUrl());

                  productCards.add(productCardsInfo);
                });
      }
    } else {
      // get the customer info from couchbase
      Customer customer = customerService.findByEmail(email);

      if (customer != null) {
        // get the helpdesk details to recommend products to the customer
        List<HelpdeskInfo> helpdeskInfoList = customer.getHelpdeskInfo();
        if (helpdeskInfoList != null && !helpdeskInfoList.isEmpty()) {
          helpdeskInfoList
              .stream()
              .forEach(
                  hd -> {
                    List<Conversations> conversationsList = hd.getConversations();
                    List<ProductCardsInfo> productCardsInfos =
                        conversationsList
                            .stream()
                            .map(c -> ftsService.getRecommendation(c.getComment()))
                            .collect(Collectors.toList());

                    productCards.addAll(productCardsInfos);
                  });
        }
      }
    }
    return productCards;
  }

  @Override
  public List<CustomerInfo> fetchCustomers(String searchText) {
    return ftsService.getCustomers(searchText);
  }
}
