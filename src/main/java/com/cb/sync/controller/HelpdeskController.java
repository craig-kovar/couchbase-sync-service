package com.cb.sync.controller;

import com.cb.sync.service.HelpdeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/zendesk")
public class HelpdeskController {

  @Autowired private HelpdeskService helpdeskService;

  @PostMapping("/upload-info")
  public void uploadZendeskInfo(@RequestParam("file") MultipartFile file) {
    helpdeskService.uploadZendeskInfo(file);
  }
}
