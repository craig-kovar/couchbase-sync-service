package com.cb.sync.service.impl;

import com.cb.sync.data.Conversations;
import com.cb.sync.data.HelpdeskInfo;
import com.cb.sync.data.ProductInfo;
import com.cb.sync.datasource.product.entity.CustomerProductDetails;
import com.cb.sync.datasource.product.entity.Orders;
import com.cb.sync.datasource.product.entity.Product;
import com.cb.sync.datasource.product.repo.CustomerProductRepo;
import com.cb.sync.datasource.user.entity.User;
import com.cb.sync.datasource.user.repo.UserRepo;
import com.cb.sync.entities.Customer;
import com.cb.sync.repo.CustomerCounterRepo;
import com.cb.sync.service.HelpdeskService;
import com.cb.sync.service.SyncService;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SyncServiceImpl implements SyncService {

  private static final Logger logger = LoggerFactory.getLogger(SyncServiceImpl.class);

  @Autowired private UserRepo userRepo;

  @Autowired private CustomerProductRepo customerProductRepo;

  @Autowired private CustomerCounterRepo customerCounterRepo;

  @Autowired private CouchbaseTemplate template;

  @Autowired private HelpdeskService helpdeskService;

  private static final String HELPDESK_INFO_CSV = "helpdesk-info.csv";

  @Override
  public void syncCustomerData() throws Exception {

    // fetch all users from Postgres datasource
    List<User> users = userRepo.findAll();

    // fetch helpdesk details from CSV file
    List<CSVRecord> csvRecords = helpdeskService.fetchZendeskDataFromCSV();

    users
        .stream()
        .forEach(
            user -> {
              Customer customer = new Customer();
              customer.setId(customerCounterRepo.counter());

              String email = user.getEmail();

              // populate customer details
              String[] ignoreUserProperties = {"id"};
              BeanUtils.copyProperties(user, customer, ignoreUserProperties);

              // get the customer product details from MySQL datasource
              List<ProductInfo> productInfos = new ArrayList<>();
              CustomerProductDetails productDetails =
                  customerProductRepo.findByCustomerEmail(email);
              if (productDetails != null) {
                List<Orders> orders = productDetails.getOrders();
                orders
                    .stream()
                    .forEach(
                        o -> {
                          ProductInfo productInfo = new ProductInfo();
                          Product product = o.getProduct();
                          productInfo.setProductId(product.getProductId());
                          productInfo.setName(product.getName());
                          productInfo.setCategory(product.getCategory());
                          productInfo.setDescription(product.getDescription());
                          productInfo.setStartDate(helpdeskService.formatDate(o.getPurchaseDate()));
                          productInfo.setEndDate(helpdeskService.formatDate(o.getExpirationDate()));
                          productInfo.setQuantity(o.getQuantity());

                          productInfos.add(productInfo);
                        });
              }
              customer.setProductInfos(productInfos);

              // populate helpdesk details
              List<HelpdeskInfo> helpdeskInfos = new ArrayList<>();
              if (csvRecords != null) {
                for (CSVRecord record : csvRecords) {

                  if (email.equalsIgnoreCase(record.get("email"))) {
                    HelpdeskInfo helpdeskInfo = new HelpdeskInfo();
                    List<String> comments = Arrays.asList(record.get("comments").split("#"));
                    List<Conversations> conversationsList = new ArrayList<>();
                    comments
                        .stream()
                        .forEach(
                            comment -> {
                              Conversations conversations = new Conversations();
                              conversations.setUser(record.get("user"));
                              conversations.setDate(record.get("date"));
                              conversations.setComment(comment);
                              conversationsList.add(conversations);
                            });
                    helpdeskInfo.setTicketNo(Long.parseLong(record.get("ticketNo")));
                    helpdeskInfo.setConversations(conversationsList);

                    helpdeskInfos.add(helpdeskInfo);
                  }
                }
                customer.setHelpdeskInfo(helpdeskInfos);

                // insert into couchbase
                template.insert(customer);
              }
            });
  }
}
