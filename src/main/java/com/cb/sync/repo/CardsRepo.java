package com.cb.sync.repo;

import com.cb.sync.entities.Cards;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;

import java.util.List;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "cards")
public interface CardsRepo extends CouchbasePagingAndSortingRepository<Cards, Long> {

  Cards findByProductName(String name);

  @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter}")
  List<Cards> findAllCards();
}
