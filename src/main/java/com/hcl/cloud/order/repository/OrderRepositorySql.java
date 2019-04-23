package com.hcl.cloud.order.repository;

import com.hcl.cloud.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is an interface which extends MongoRepository interface. It will be responsible to talk to mongo database.
 * @author shikhar.a || ankit-kumar
 */
@Repository
public interface OrderRepositorySql extends JpaRepository<Order, Long> {

}
