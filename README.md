## couchbase-sync-service
Microservice that is used to sync data from multiple datasource to Couchbase using Spring-Boot and Spring-Data.


### Technology Stack
Component         | Technology
---               | ---
Backend (REST)    | [SpringBoot](https://projects.spring.io/spring-boot) (Java)
REST Spec         | [Open API Standard](https://www.openapis.org/) 
DB                | MySQL (Holds product data)
DB                | PostgreSQL (Holds customer data)
Persistence       | JPA (Using Spring Data)
Server Build Tools| Maven(Java)

## Folder Structure
```bash
PROJECT_FOLDER
│  README.md
│  pom.xml           
└──[src]      
│  └──[main]      
│     └──[java]      
│     └──[resources]
│        │  application.properties #contains springboot cofigurations
│        │  mysql-db.sql        # Contains DB Script to create tables and data for product    
│        │  postgresql-db.sql   # Contains DB Script to create tables and data for customer    
│              
│
└──[target]     #Java build files, auto-created after running java build: mvn install
  └──[classes]

```

## Prerequisites
Ensure you have this installed before proceeding further.
- Java 8
- Maven 3 or later
- MySQL 5.6 or later
- PostgreSQL  


### Build Backend (SpringBoot Java)
```bash
# Maven Build : Navigate to the root folder where pom.xml is present 
mvn clean install
mvn spring-boot:run

```














