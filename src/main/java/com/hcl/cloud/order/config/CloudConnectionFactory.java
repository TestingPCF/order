// package com.hcl.cloud.order.config;
//
// import com.mongodb.MongoClient;
// import org.springframework.cloud.config.java.AbstractCloudConfig;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Profile;
// import org.springframework.data.authentication.UserCredentials;
// import org.springframework.data.mongodb.MongoDbFactory;
// import org.springframework.data.mongodb.core.MongoTemplate;
// import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
//
// @Configuration
// @Profile("cloud")
// public class CloudConnectionFactory extends AbstractCloudConfig {
//
// @Bean
// public MongoDbFactory mongoDbFactory(){
// MongoClient mongoClient = new
// MongoClient("d46de95-mongodb-0.node.dc1.a9s-mongodb-consul"
// , 27017);
// UserCredentials credentials = new
// UserCredentials("a9s-brk-usr-b1786eb95e105117cdbc7e37c5686f14dfa7204d"
// , "a9sf1e975c419f0a1d9a2c9a0c1d36fdc2b0e19b066");
//
// return new SimpleMongoDbFactory(mongoClient, "d46de95", credentials);
// }
//
// @Bean
// public MongoTemplate mongoTemplate(){
// MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
// return mongoTemplate;
// }
//
//
//
// }
