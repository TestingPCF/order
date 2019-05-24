package com.hcl.cloud.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("cqrs-queryside")
public interface CartServiceClient {

    @GetMapping("/query/cart")
    public ResponseEntity<Object> getCart(@RequestHeader("accessToken") String accessToken);

}
