package com.hcl.cloud.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hcl.cloud.order.entity.Inventory;

@FeignClient("inventory")
public interface InventoryServiceClient {
    @GetMapping("/{skuCode}/{quantity}")
    public ResponseEntity<Object> readInventory(@PathVariable String skuCode, @PathVariable int quantity);

    @PutMapping
    public ResponseEntity<Object> updateInventory(@RequestBody Inventory inventory);

}
