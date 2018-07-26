package com.cb.sync.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "productEntityManagerFactory",
    transactionManagerRef = "productTransactionManager",
    basePackages = {"com.cb.sync.datasource.product.repo"})
public class ProductDbConfig {

  @Autowired private Environment env;

  @Primary
  @Bean(name = "productDataSource")
  @ConfigurationProperties(prefix = "product.datasource")
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(env.getProperty("product.datasource.driver-class-name"));
    dataSource.setUrl(env.getProperty("product.datasource.url"));
    dataSource.setUsername(env.getProperty("product.datasource.username"));
    dataSource.setPassword(env.getProperty("product.datasource.password"));

    return dataSource;
  }

  @Primary
  @Bean(name = "productEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean productEntityManagerFactory(
      EntityManagerFactoryBuilder builder, @Qualifier("productDataSource") DataSource dataSource) {
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("hibernate.hbm2ddl.auto", "update");
    properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
    return builder
        .dataSource(dataSource)
        .packages("com.cb.sync.datasource.product.entity")
        .persistenceUnit("product")
        .properties(properties)
        .build();
  }

  @Primary
  @Bean(name = "productTransactionManager")
  public PlatformTransactionManager productTransactionManager(
      @Qualifier("productEntityManagerFactory") EntityManagerFactory productEntityManagerFactory) {
    return new JpaTransactionManager(productEntityManagerFactory);
  }
}
