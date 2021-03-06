package com.hcl.cloud.order.client;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import com.hcl.cloud.order.constant.OrderConstant;
import com.hcl.cloud.order.dto.CartItem;
import com.hcl.cloud.order.dto.CartResponse;
import com.hcl.cloud.order.entity.Inventory;

/**
 * This class will treat as a client for rest apis.
 * 
 * @author shikhar.a || ankit-kumar || mohitkri
 */
@Component
public class RestClient {
    /**
     * Instance of InventoryServiceClient.
     */
    private static InventoryServiceClient inventoryServiceClient;

    /**
     * Instance of CartServiceClient.
     */
    private static CartServiceClient cartServiceClient;

    /**
     * autowiring InventoryServiceClient.
     * 
     * @param client
     *            InventoryServiceClient
     */
    @Autowired
    public void setInventoryServiceClient(InventoryServiceClient client) {
        RestClient.inventoryServiceClient = client;
    }

    /**
     * @param client
     */
    @Autowired
    public void setCartServiceClient(CartServiceClient client) {
        RestClient.cartServiceClient = client;
    }

    /**
     * cartUri.
     */
    // private static String cartUri = "//cart";

    /**
     * logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RestClient.class);

    /**
     * getResponseFromMS.
     * 
     * @param serviceName
     *            Name of the service
     * @param object
     *            Object
     * @param authorization
     *            Authorization
     * @return ResponseEntity prepared response entity
     */
    public static ResponseEntity<Object> getResponseFromMS(final String serviceName, final Object object,
            final String authorization) {
        try {
            // RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set(OrderConstant.AUTHORIZATION_TOKEN, authorization);
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

            switch (serviceName) {
            case "cart":
                try {
                    /*
                     * ResponseEntity<Object> response = restTemplate.exchange(cartUri,
                     * HttpMethod.GET, entity, Object.class);
                     */
                    LOGGER.info(" Calling cart Read using FeignClient using class : ");
                    ResponseEntity<Object> response = cartServiceClient.getCart(authorization);
                    return response;
                } catch (HttpClientErrorException e) {
                    throw e;
                }
            case "inventoryRead":
                CartResponse cart = (CartResponse) object;
                if (cart.getData().getCartItems().isEmpty()) {
                    return new ResponseEntity<Object>(HttpStatus.EXPECTATION_FAILED);
                }
                for (CartItem shoppingItem : cart.getData().getCartItems()) {
                    final Map<String, String> params = new HashMap<>();
                    params.put("skuCode", shoppingItem.getItemCode());
                    params.put("quantity", shoppingItem.getQuantity() + "");
                    try {
                        LOGGER.info(" Calling Inventory Read using FeignClient using class : ");
                        ResponseEntity<Object> inventoryResponse = inventoryServiceClient
                                .readInventory(shoppingItem.getItemCode(), shoppingItem.getQuantity());

                        if (!(Boolean) inventoryResponse.getBody()) {
                            return new ResponseEntity<Object>(HttpStatus.EXPECTATION_FAILED);
                        }
                    } catch (HttpClientErrorException e) {
                        throw e;
                    }
                }
                return new ResponseEntity<Object>(HttpStatus.OK);
            case "inventoryUpdate":
                CartResponse cartItem = (CartResponse) object;
                for (CartItem shoppingItem : cartItem.getData().getCartItems()) {
                    Inventory inventory = new Inventory(shoppingItem.getItemCode(), shoppingItem.getQuantity());
                    try {
                        // restTemplate.put(inventorUpdateUri, inventory);
                        LOGGER.info(" Calling Inventory Update using FeignClient using class : ");
                        inventoryServiceClient.updateInventory(inventory);
                    } catch (HttpClientErrorException e) {
                        throw e;
                    }
                }
                return new ResponseEntity<Object>(HttpStatus.OK);
            default:
                return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);

            }

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Print Method.
     */
    public final void printApplication() {
        System.out.println("Rest Client initialized::");
    }

    /**
     * setCartUri.
     * 
     * @param cartUriParam
     *            cartUri
     */
    /*
     * @Value("${cartUri}") public final void setCartUri(final String cartUriParam)
     * { this.cartUri = cartUriParam; }
     */
}
