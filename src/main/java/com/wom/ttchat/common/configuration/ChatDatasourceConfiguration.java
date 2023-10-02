//package com.wom.ttchat.common.configuration;
//
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class ChatDatasourceConfiguration {
//    @Primary
//    @Bean
//    @ConfigurationProperties("spring.datasource.chat")
//    public DataSourceProperties chatDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Primary
//    @Bean
//    public DataSource chatDataSource() {
//        return chatDataSourceProperties()
//                .initializeDataSourceBuilder()
//                .build();
//    }
//}
