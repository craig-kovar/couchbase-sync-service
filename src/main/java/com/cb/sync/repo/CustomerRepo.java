package com.cb.sync.repo;

import com.cb.sync.entities.Customer;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;

import java.util.List;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "customer")
public interface CustomerRepo extends CouchbasePagingAndSortingRepository<Customer, Long> {

  Customer findByEmail(String email);

  @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter}")
  List<Customer> findAllCustomers();
}
