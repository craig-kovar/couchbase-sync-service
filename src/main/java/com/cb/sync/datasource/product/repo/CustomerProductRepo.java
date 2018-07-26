package com.cb.sync.datasource.product.repo;

import com.cb.sync.datasource.product.entity.CustomerProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerProductRepo extends JpaRepository<CustomerProductDetails, String> {

  //  @Query("SELECT c FROM CustomerProductDetails c WHERE c.customerEmail = (:email)")
  CustomerProductDetails findByCustomerEmail(String email);
}
