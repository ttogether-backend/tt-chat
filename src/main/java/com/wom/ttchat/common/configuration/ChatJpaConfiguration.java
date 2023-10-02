//package com.wom.ttchat.common.configuration;
//
//import com.wom.ttchat.chatroom.adapter.out.entity.ChatRoomJpaEntity;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
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
//        basePackages = "com.wom.ttchat.chatroom",
//        entityManagerFactoryRef = "chatEntityManagerFactory",
//        transactionManagerRef = "chatTransactionManager"
//)
//public class ChatJpaConfiguration {
//
//    @Primary
//    @Bean
//    public LocalContainerEntityManagerFactoryBean chatEntityManagerFactory(
//           @Qualifier("chatDataSource") DataSource dataSource,
//           EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(dataSource)
//                .packages(ChatRoomJpaEntity.class)
//                .build();
//    }
//
//    @Primary
//    @Bean
//    public PlatformTransactionManager chatTransactionManager(
//            @Qualifier("chatEntityManagerFactory") LocalContainerEntityManagerFactoryBean chatEntityManagerFactory
//    ) {
//        return new JpaTransactionManager(Objects.requireNonNull(chatEntityManagerFactory.getObject()));
//    }
//}
