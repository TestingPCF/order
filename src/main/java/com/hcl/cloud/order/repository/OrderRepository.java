package com.hcl.cloud.order.repository;

import com.hcl.cloud.order.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This is an interface which extends MongoRepository interface. It will be responsible to talk to mongo database.
 * @author shikhar.a || ankit-kumar
 */
@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

}
