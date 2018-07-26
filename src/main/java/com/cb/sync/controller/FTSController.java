package com.cb.sync.controller;

import com.cb.sync.service.FTSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fts")
public class FTSController {

  @Autowired private FTSService ftsService;

  @PostMapping("/search-metadata")
  public void searchMetadata(@RequestParam("searchText") String searchText) throws Exception {
    ftsService.findByTextMatch(searchText);
  }
}
