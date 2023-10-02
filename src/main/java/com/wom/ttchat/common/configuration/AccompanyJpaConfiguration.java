//package com.wom.ttchat.common.configuration;
//
//import com.wom.ttchat.accompany.adapter.out.persistence.AccompanyJpaEntity;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.Objects;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        basePackages = "com.wom.ttchat.accompany",
//        entityManagerFactoryRef = "accompanyEntityManagerFactory",
//        transactionManagerRef = "accompanyTransactionManager"
//)
//public class AccompanyJpaConfiguration {
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean accompanyEntityManagerFactory(
//            @Qualifier("accompanyDataSource") DataSource dataSource,
//            EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(dataSource)
//                .packages(AccompanyJpaEntity.class)
//                .build();
//    }
//
//    @Bean
//    public PlatformTransactionManager accompanyTransactionManager(
//            @Qualifier("accompanyEntityManagerFactory") LocalContainerEntityManagerFactoryBean accompanyEntityManagerFactory
//    ) {
//        return new JpaTransactionManager(Objects.requireNonNull(accompanyEntityManagerFactory.getObject()));
//    }
//}
