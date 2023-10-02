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
//public class MemberDatasourceConfiguration {
//    @Bean
//    @ConfigurationProperties("spring.datasource.member")
//    public DataSourceProperties memberDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean
//    public DataSource memberDataSource() {
//        return memberDataSourceProperties()
//                .initializeDataSourceBuilder()
//                .build();
//    }
//}
