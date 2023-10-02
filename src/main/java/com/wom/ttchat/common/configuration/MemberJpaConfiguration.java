//package com.wom.ttchat.common.configuration;
//
//import com.wom.ttchat.member.adapter.out.persistence.MemberJpaEntity;
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
//        basePackages = "com.wom.ttchat.member",
//        entityManagerFactoryRef = "memberEntityManagerFactory",
//        transactionManagerRef = "memberTransactionManager"
//)
//public class MemberJpaConfiguration {
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean memberEntityManagerFactory(
//            @Qualifier("memberDataSource") DataSource dataSource,
//            EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(dataSource)
//                .packages(MemberJpaEntity.class)
//                .build();
//    }
//
//    @Bean
//    public PlatformTransactionManager memberTransactionManager(
//            @Qualifier("memberEntityManagerFactory") LocalContainerEntityManagerFactoryBean memberEntityManagerFactory
//    ) {
//        return new JpaTransactionManager(Objects.requireNonNull(memberEntityManagerFactory.getObject()));
//    }
//}
