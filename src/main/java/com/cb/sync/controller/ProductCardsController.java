package com.cb.sync.controller;

import com.cb.sync.data.CustomerInfo;
import com.cb.sync.data.ProductCardsInfo;
import com.cb.sync.data.vo.CardsVO;
import com.cb.sync.entities.Cards;
import com.cb.sync.service.ProductCardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class ProductCardsController {

  @Autowired
  private ProductCardsService cardsService;

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public Cards create(@Valid @RequestBody Cards cards) {
    return cardsService.create(cards);
  }

  @PostMapping("/product-recommendation")
  public List<ProductCardsInfo> getProductRecommendation(@RequestBody CardsVO cardsVO) {
    return cardsService.getProductRecommendation(cardsVO.getEmail());
  }

  @PostMapping("/customers")
  public List<CustomerInfo> fetchCustomersForCards(@RequestBody CardsVO cardsVO) {
    return cardsService.fetchCustomers(cardsVO.getSearchText());
  }
}
