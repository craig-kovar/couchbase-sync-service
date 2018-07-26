package com.cb.sync.controller;

import com.cb.sync.service.SyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sync")
public class SyncController {

  @Autowired
  private SyncService syncService;

  @PostMapping("/customer-data")
  public void syncCustomerData() throws Exception {
    syncService.syncCustomerData();
  }
}
