//package com.wom.ttchat.common.configuration;
//
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class AccompanyDatasourceConfiguration {
//    @Bean
//    @ConfigurationProperties("spring.datasource.together")
//    public DataSourceProperties accompanyDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean
//    public DataSource accompanyDataSource() {
//        return accompanyDataSourceProperties()
//                .initializeDataSourceBuilder()
//                .build();
//    }
//}