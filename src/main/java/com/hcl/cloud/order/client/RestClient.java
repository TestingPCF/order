package com.hcl.cloud.order.client;

import com.hcl.cloud.order.constant.OrderConstant;
import com.hcl.cloud.order.dto.CartItem;
import com.hcl.cloud.order.dto.CartResponse;
import com.hcl.cloud.order.entity.Inventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * This class will treat as a client for rest apis.
 * @author shikhar.a || ankit-kumar
 */
@Component
public class RestClient {

    /**
     * inventorReadUri.
     */
    public static String inventorReadUri;

    /**
     * cartUri.
     */
    public static String cartUri;

    /**
     * inventorUpdateUri.
     */
    public static String inventorUpdateUri;

    /**
     * logger.
     */
    private static final Logger LOGGER =
            LoggerFactory.getLogger(RestClient.class);

    /**
     * getResponseFromMS.
     * @param serviceName Name of the service
     * @param object Object
     * @param authorization Authorization
     * @return ResponseEntity prepared response entity
     */
    public static ResponseEntity<Object> getResponseFromMS(
            final String serviceName,
            final Object object,
           final String authorization) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set(OrderConstant.AUTHORIZATION_TOKEN, authorization);
            HttpEntity<String> entity = new HttpEntity<>("parameters",
                    headers);

            switch (serviceName) {
                case "cart":
                    ResponseEntity<Object> response =
                            restTemplate.exchange(cartUri,
                                    HttpMethod.GET,
                                    entity,
                                    Object.class);
                    return response;
                case "inventoryRead":
                    CartResponse cart = (CartResponse) object;
                    for(CartItem shoppingItem :cart.getData().getCartItems()) {
                        final Map<String, String> params = new HashMap<>();
                        params.put("skuCode", shoppingItem.getItemCode());
                        params.put("quantity", shoppingItem.getQuantity()+"");
                        ResponseEntity<Object> inventoryResponse =
                                restTemplate.exchange(inventorReadUri,
                                        HttpMethod.GET,
                                        entity, Object.class, params);
                        if(!(Boolean)inventoryResponse.getBody()){
                            return new ResponseEntity<Object>(HttpStatus.EXPECTATION_FAILED);
                        }
                    }
                    return  new ResponseEntity<Object>(HttpStatus.OK);
                case "inventoryUpdate":
                    CartResponse cartItem = (CartResponse) object;
                    for(CartItem shoppingItem :cartItem.getData().getCartItems()) {
                        Inventory inventory =
                                new Inventory(shoppingItem.getItemCode(),
                                        shoppingItem.getQuantity());
                        restTemplate.put(inventorUpdateUri, inventory);
                    }
                    return new ResponseEntity<Object>(HttpStatus.OK);
                    default:
                        return new ResponseEntity<Object>(HttpStatus
                                .INTERNAL_SERVER_ERROR);

            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Print Method.
     */
    public final void printApplication() {
        System.out.println("Rest Client initialized::");
    }

    /**
     * inventorReadUri.
     * @param inventorReadUri inventorReadUri
     */
    @Value("${inventorReadUri}")
    public void setInventoryUri(String inventorReadUri) {
        this.inventorReadUri = inventorReadUri;
    }

    /**
     * setInventorUpdateUri.
     * @param inventorUpdateUri inventorUpdateUri
     */
    @Value("${inventorUpdateUri}")
    public void setInventorUpdateUri(String inventorUpdateUri) {
        this.inventorUpdateUri = inventorUpdateUri;
    }

    /**
     * setCartUri.
     * @param cartUri cartUri
     */
    @Value("${cartUri}")
    public void setCartUri(String cartUri) {
        this.cartUri = cartUri;
    }
}