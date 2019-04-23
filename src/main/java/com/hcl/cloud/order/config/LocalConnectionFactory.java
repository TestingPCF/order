//package com.hcl.cloud.order.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import javax.sql.DataSource;
//
///**
// * This is a connection factory which will return us the database connection for dev environment.
// * We are following Strategic pattern here using profiling concept.
// */
////@Profile("dev")
////@Configuration
////public class LocalConnectionFactory {
////
////    @Bean
////    public DataSource getLocalDataSource(){
////        DriverManagerDataSource dataSource = new DriverManagerDataSource("jdbc:mysql://localhost:3306/order","root","admin");
////        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
////        return dataSource;
////    }
////}
