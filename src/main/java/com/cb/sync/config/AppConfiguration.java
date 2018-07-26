package com.cb.sync.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Application config class
 *
 * @author Balaji
 */
@EnableConfigurationProperties({
  com.cb.sync.config.CouchbaseSetting.class /* other setting classes */
})
public class AppConfiguration {}
