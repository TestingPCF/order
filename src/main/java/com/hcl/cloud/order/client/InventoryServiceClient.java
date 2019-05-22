package com.hcl.cloud.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hcl.cloud.order.entity.Inventory;

/**
 * This is an interface, which works as a client for inventory API.
 * @author mohitkri
 */
@FeignClient("inventory")
public interface InventoryServiceClient {
    /**
     * This method invokes the GET api call for sku and quantity.
     * @param skuCode sku code
     * @param quantity quantity
     * @return ResponseEntity ResponseEntity
     */
    @GetMapping("/{skuCode}/{quantity}")
    public ResponseEntity<Object> readInventory(@PathVariable String skuCode
            , @PathVariable int quantity);

    /**
     * This method invokes the PUT api call for inventory.
     * @param inventory inventory object
     * @return ResponseEntity ResponseEntity
     */
    @PutMapping
    public ResponseEntity<Object> updateInventory(@RequestBody Inventory inventory);

}
