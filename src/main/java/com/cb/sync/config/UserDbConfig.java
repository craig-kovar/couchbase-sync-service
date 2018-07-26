package com.cb.sync.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    entityManagerFactoryRef = "userEntityManagerFactory",
    transactionManagerRef = "userTransactionManager",
    basePackages = {"com.cb.sync.datasource.user.repo"})
public class UserDbConfig {

  @Autowired private Environment env;

  @Bean(name = "userDataSource")
  @ConfigurationProperties(prefix = "user.datasource")
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(env.getProperty("user.datasource.driver-class-name"));
    dataSource.setUrl(env.getProperty("user.datasource.url"));
    dataSource.setUsername(env.getProperty("user.datasource.username"));
    dataSource.setPassword(env.getProperty("user.datasource.password"));

    return dataSource;
  }

  @Bean(name = "userEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean userEntityManagerFactory(
      EntityManagerFactoryBuilder builder, @Qualifier("userDataSource") DataSource dataSource) {
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("hibernate.hbm2ddl.auto", "update");
    properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
    return builder
        .dataSource(dataSource)
        .packages("com.cb.sync.datasource.user.entity")
        .persistenceUnit("user")
        .properties(properties)
        .build();
  }

  @Bean(name = "userTransactionManager")
  public PlatformTransactionManager userTransactionManager(
      @Qualifier("userEntityManagerFactory") EntityManagerFactory userEntityManagerFactory) {
    return new JpaTransactionManager(userEntityManagerFactory);
  }
}
