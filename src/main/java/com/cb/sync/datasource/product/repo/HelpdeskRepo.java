package com.cb.sync.datasource.product.repo;

import com.cb.sync.datasource.product.entity.Helpdesk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HelpdeskRepo extends JpaRepository<Helpdesk, String> {

  List<Helpdesk> findByCustomerEmail(String email);
}
