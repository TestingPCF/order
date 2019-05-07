package com.hcl.cloud.order.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.cloud.order.client.RestClient;
import com.hcl.cloud.order.constant.OrderConstant;
import com.hcl.cloud.order.dto.CartItem;
import com.hcl.cloud.order.dto.CartResponse;
import com.hcl.cloud.order.dto.ShoppingCart;
import com.hcl.cloud.order.entity.Cart;
import com.hcl.cloud.order.entity.Order;
import com.hcl.cloud.order.entity.ShoppingItem;
import com.hcl.cloud.order.exception.BadRequestException;
import com.hcl.cloud.order.repository.OrderRepositorySql;
import com.hcl.cloud.order.util.ResponseUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/*
 * Test class for OrderServiceImpl.
 * @author shikhar.a || ankit-kumar
 * */


@RunWith(value = PowerMockRunner.class)
@PrepareForTest({Optional.class, Order.class, RestClient.class,
        ResponseUtil.class})
public class OrderServiceImplTest {

    /**
     * ORDER_ID.
     */
    private final String ORDER_ID = "ORDER_ID";

    /**
     * ACCESS_TOKEN.
     */
    private final String ACCESS_TOKEN = "ACCESS_TOKEN";

    /**
     * TEST_STRING.
     */
    private final String TEST_STRING = "TEST_STRING";

    /**
     * OrderServiceImpl Mock.
     */
    @InjectMocks
    private OrderServiceImpl orderServiceImpl;

    /**
     * OrderRepository Mock.
     **/
    @Mock
    private OrderRepositorySql orderRepository;

    /**
     * Order Mock.
     **/
    @Mock
    private Order order;

    /**
     * Order Mock.
     **/
    @Mock
    private Cart cartMock;

    /**
     * responseMock.
     */
    @Mock
    ResponseEntity<Object> responseMock;

    /**
     * Object Mock.
     **/
    @Mock
    private Object objectMock;

    /**
     * CartResponse Mock.
     **/
    @Mock
    private CartResponse cartResponse;

    /**
     * ORDER_ID.
     */
    private static final Long ORDER_ID_MOCK = 1l;

    /**
     * This Method is called before the test is executed.
     *
     * @throws Exception
     **/

    @Before
    public void setUp() throws Exception {
        this.orderServiceImpl = PowerMockito.spy(new OrderServiceImpl());
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Success Test for checkout Method.
     **/
    @Test
    public final void testCheckoutSuccess() throws IOException {
        List<CartItem> cartItems = new ArrayList<>();
        CartItem cartItem = new CartItem();
        cartItem.setListPrice(new BigDecimal(10));
        cartItem.setSalePrice(new BigDecimal(10));
        cartItem.setQuantity(1);
        cartItems.add(cartItem);
        PowerMockito.mockStatic(RestClient.class);
        PowerMockito.when(RestClient.getResponseFromMS(OrderConstant
                        .INVERNTORY_CART,
                null ,ACCESS_TOKEN)).thenReturn(responseMock);

        PowerMockito.mockStatic(ResponseUtil.class);
        PowerMockito.when(ResponseUtil.getCartResponse(responseMock))
                .thenReturn(cartResponse);
        order.setUserEmail(TEST_STRING);

        ShoppingItem shoppingItem = new ShoppingItem(null, null
                , null, null,
                null);
        shoppingItem.setListPrice(new BigDecimal(10));
        shoppingItem.getOrder();
        shoppingItem.setQuantity(1);
        shoppingItem.setSalePrice(new BigDecimal(10));
        shoppingItem.getShoppingItemId();
        shoppingItem.getSkuCode();
        shoppingItem.getTotalPrice();
        ShoppingItem shoppingItem2 = new ShoppingItem(null, null
                , null, null,
                null, null, null);

        PowerMockito.when(RestClient.getResponseFromMS(OrderConstant
                        .INVERNTORY_READ,
                cartResponse ,ACCESS_TOKEN)).thenReturn(responseMock);
        ShoppingCart shoppingCart = new ShoppingCart();
        PowerMockito.when(cartResponse.getData()).thenReturn(shoppingCart);
        shoppingCart.setCartItems(cartItems);
        PowerMockito.when(orderRepository.save(order)).thenReturn(order);
        orderServiceImpl.checkout(order, ACCESS_TOKEN);
    }

    /**
     * Failed Test for checkout Method.
     **/
    @Test
    public final void testCheckoutFail() throws IOException {
        List<CartItem> cartItems = new ArrayList<>();
        CartItem cartItem = new CartItem();
        cartItems.add(cartItem);
        PowerMockito.mockStatic(RestClient.class);
        PowerMockito.when(RestClient.getResponseFromMS(OrderConstant
                        .INVERNTORY_CART,
                null ,ACCESS_TOKEN)).thenReturn(responseMock);

        PowerMockito.mockStatic(ResponseUtil.class);
        PowerMockito.when(ResponseUtil.getCartResponse(responseMock))
                .thenReturn(cartResponse);
        order.setUserEmail(TEST_STRING);

        PowerMockito.when(RestClient.getResponseFromMS(OrderConstant
                        .INVERNTORY_READ,
                cartResponse ,ACCESS_TOKEN)).thenReturn(responseMock);

        PowerMockito.when(responseMock.getStatusCode())
                .thenReturn(HttpStatus.EXPECTATION_FAILED);

        orderServiceImpl.checkout(order, ACCESS_TOKEN);
    }

    /**
     * Failed Test for checkout Method.
     **/
    @Test
    public final void testCheckoutCartClientFail() throws IOException {
        List<CartItem> cartItems = new ArrayList<>();
        CartItem cartItem = new CartItem();
        cartItems.add(cartItem);
        PowerMockito.mockStatic(RestClient.class);
        PowerMockito.when(RestClient.getResponseFromMS(OrderConstant
                        .INVERNTORY_CART,
                null ,ACCESS_TOKEN))
                .thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        orderServiceImpl.checkout(order, ACCESS_TOKEN);
    }

    /**
     * Failed Test for checkout Method.
     **/
    @Test
    public final void testCheckoutCartClientInternalFail() throws IOException {
        List<CartItem> cartItems = new ArrayList<>();
        CartItem cartItem = new CartItem();
        cartItems.add(cartItem);
        PowerMockito.mockStatic(RestClient.class);
        PowerMockito.when(RestClient.getResponseFromMS(OrderConstant
                        .INVERNTORY_CART,
                null ,ACCESS_TOKEN))
                .thenThrow(new RuntimeException());
        orderServiceImpl.checkout(order, ACCESS_TOKEN);
    }

    /**
     * Failed Test for checkout Method.
     **/
    @Test
    public final void testCheckoutInventoryInternalClientFail()
            throws IOException {
        List<CartItem> cartItems = new ArrayList<>();
        CartItem cartItem = new CartItem();
        cartItems.add(cartItem);
        PowerMockito.mockStatic(RestClient.class);
        PowerMockito.when(RestClient.getResponseFromMS(OrderConstant
                        .INVERNTORY_CART,
                null ,ACCESS_TOKEN)).thenReturn(responseMock);

        PowerMockito.mockStatic(ResponseUtil.class);
        PowerMockito.when(ResponseUtil.getCartResponse(responseMock))
                .thenReturn(cartResponse);
        order.setUserEmail(TEST_STRING);

        PowerMockito.when(RestClient.getResponseFromMS(OrderConstant
                        .INVERNTORY_READ,
                cartResponse ,ACCESS_TOKEN)).thenThrow(new RuntimeException());

        PowerMockito.when(responseMock.getStatusCode())
                .thenReturn(HttpStatus.EXPECTATION_FAILED);

        orderServiceImpl.checkout(order, ACCESS_TOKEN);
    }

    /**
     * Failed Test for checkout Method.
     **/
    @Test
    public final void testCheckoutInventoryClientFail()
            throws IOException {
        List<CartItem> cartItems = new ArrayList<>();
        CartItem cartItem = new CartItem();
        cartItems.add(cartItem);
        PowerMockito.mockStatic(RestClient.class);
        PowerMockito.when(RestClient.getResponseFromMS(OrderConstant
                        .INVERNTORY_CART,
                null ,ACCESS_TOKEN)).thenReturn(responseMock);

        PowerMockito.mockStatic(ResponseUtil.class);
        PowerMockito.when(ResponseUtil.getCartResponse(responseMock))
                .thenReturn(cartResponse);
        order.setUserEmail(TEST_STRING);

        PowerMockito.when(RestClient.getResponseFromMS(OrderConstant
                        .INVERNTORY_READ,
                cartResponse, ACCESS_TOKEN))
                .thenThrow(new HttpClientErrorException(HttpStatus
                        .BAD_REQUEST));

        PowerMockito.when(responseMock.getStatusCode())
                .thenReturn(HttpStatus.EXPECTATION_FAILED);

        orderServiceImpl.checkout(order, ACCESS_TOKEN);
    }

    /**
     * Failed Test for checkout Method.
     **/
    @Test
    public final void testCheckoutInventoryClientNotFoundFail()
            throws IOException {
        List<CartItem> cartItems = new ArrayList<>();
        CartItem cartItem = new CartItem();
        cartItems.add(cartItem);
        PowerMockito.mockStatic(RestClient.class);
        PowerMockito.when(RestClient.getResponseFromMS(OrderConstant
                        .INVERNTORY_CART,
                null ,ACCESS_TOKEN)).thenReturn(responseMock);

        PowerMockito.mockStatic(ResponseUtil.class);
        PowerMockito.when(ResponseUtil.getCartResponse(responseMock))
                .thenReturn(cartResponse);
        order.setUserEmail(TEST_STRING);

        PowerMockito.when(RestClient.getResponseFromMS(OrderConstant
                        .INVERNTORY_READ,
                cartResponse, ACCESS_TOKEN))
                .thenThrow(new HttpClientErrorException(HttpStatus
                        .NOT_FOUND));

        PowerMockito.when(responseMock.getStatusCode())
                .thenReturn(HttpStatus.SERVICE_UNAVAILABLE);

        orderServiceImpl.checkout(order, ACCESS_TOKEN);
    }

    /**
     * Success Test for updateOrder Method.
     **/

    @Test
    public final void testUpdateOrderSuccess() {
        Order order = new Order();
        order.setOrderId(ORDER_ID_MOCK);
        PowerMockito.when(orderRepository.findById(ORDER_ID_MOCK))
                .thenReturn(Optional.of(order));
        order.setOrderStatus(TEST_STRING);
        order.setDeliveryDate(new Date());
        PowerMockito.when(orderRepository.save(order)).thenReturn(order);
        orderServiceImpl.updateOrder(order);
    }

    /**
     * Fail Test for updateOrder Method.
     **/

    @Test(expected = BadRequestException.class)
    public final void testUpdateOrderFail() {
        PowerMockito.when(orderRepository.save(order)).thenReturn(order);
        orderServiceImpl.updateOrder(order);
    }

    /**
     * Fail Test for getOrders Method.
     **/

    @Test
    public final void testGetOrderNullFailure() {
        PowerMockito.when(orderRepository.getOne(ORDER_ID_MOCK)).thenReturn(order);
        orderServiceImpl.getOrder(ORDER_ID_MOCK);
    }

    /**
     * Success Test for getOrders Method.
     **/

    @Test
    public final void testGetOrderSuccess() {
        PowerMockito.when(orderRepository.findById(ORDER_ID_MOCK))
                .thenReturn(Optional.of(order));
        orderServiceImpl.getOrder(ORDER_ID_MOCK);
    }

    /**
     * Success Test for getAllOrders Method.
     **/
    @Test
    public final void testGetAllOrdersSuccess() {
        PowerMockito.when(orderRepository.findAll()).thenReturn(Collections.emptyList());
        orderServiceImpl.getAllOrders();
    }
}
