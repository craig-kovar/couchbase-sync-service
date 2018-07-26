package com.cb.sync.service;

import com.cb.sync.datasource.product.entity.Helpdesk;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface HelpdeskService {

  Helpdesk findByEmail(String email);

  void uploadZendeskInfo(MultipartFile file);

  List<CSVRecord> fetchZendeskDataFromCSV() throws Exception;

  String formatDate(Date date);
}
