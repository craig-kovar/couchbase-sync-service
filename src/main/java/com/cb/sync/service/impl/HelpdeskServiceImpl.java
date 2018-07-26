package com.cb.sync.service.impl;

import com.cb.sync.datasource.product.entity.Helpdesk;
import com.cb.sync.datasource.product.repo.HelpdeskRepo;
import com.cb.sync.exception.ApiException;
import com.cb.sync.exception.ErrorType;
import com.cb.sync.service.HelpdeskService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class HelpdeskServiceImpl implements HelpdeskService {

  private static final String HELPDESK_INFO_CSV = "helpdesk-info.csv";

  @Autowired private HelpdeskRepo helpdeskRepo;

  @Override
  public Helpdesk findByEmail(String email) {
    return null;
  }

  @Override
  public void uploadZendeskInfo(MultipartFile file) {

    try {
      Iterable<CSVRecord> records =
          CSVFormat.DEFAULT
              .withFirstRecordAsHeader()
              .parse(
                  new InputStreamReader(
                      new BOMInputStream(file.getInputStream()), StandardCharsets.UTF_8));

      for (CSVRecord record : records) {
        String email = record.get("email");
        List<String> questions = Arrays.asList(record.get("questions").split("#"));

        questions
            .parallelStream()
            .forEach(
                question -> {
                  Helpdesk helpdesk = new Helpdesk();
                  helpdesk.setCustomerEmail(email);
                  helpdesk.setQuestion(question);

                  helpdeskRepo.save(helpdesk);
                });
      }
    } catch (Exception e) {
      throw new ApiException(ErrorType.UPLOAD_FAILED);
    }
  }

  @Override
  public List<CSVRecord> fetchZendeskDataFromCSV() throws Exception {

    String path = new ClassPathResource(HELPDESK_INFO_CSV).getFile().getAbsolutePath();
    Reader reader = Files.newBufferedReader(Paths.get(path));
    CSVParser csvParser =
        new CSVParser(
            reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

      List<CSVRecord> csvRecords = null;
      try {
          csvRecords = csvParser.getRecords();
      } catch (IOException e) {
          e.printStackTrace();
      }
    return csvRecords;
  }

  @Override
  public String formatDate(Date date) {
      SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
      return formatter.format(date);
  }
}
