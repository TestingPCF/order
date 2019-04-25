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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
     * ShoppingCart.
     */
    @Mock
    private ShoppingCart shoppingCart;

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
        shoppingItem.getListPrice();
        shoppingItem.getOrder();
        shoppingItem.getQuantity();
        shoppingItem.getSalePrice();
        shoppingItem.getShoppingItemId();
        shoppingItem.getSkuCode();
        shoppingItem.getTotalPrice();
        ShoppingItem shoppingItem2 = new ShoppingItem(null, null
                , null, null,
                null, null, null);

        PowerMockito.when(RestClient.getResponseFromMS(OrderConstant
                        .INVERNTORY_READ,
                cartResponse ,ACCESS_TOKEN)).thenReturn(responseMock);
        PowerMockito.when(cartResponse.getData()).thenReturn(shoppingCart);
        PowerMockito.when(shoppingCart.getCartItems()).thenReturn(cartItems);
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
     * Success Test for updateOrder Method.
     **/

    @Test
    public final void testUpdateOrderSuccess() {
        PowerMockito.when(orderRepository.save(order)).thenReturn(order);
        orderServiceImpl.updateOrder(order);
    }

    /**
     * Success Test for getOrders Method.
     **/

    @Test
    public final void testGetOrderSuccess() {
        PowerMockito.when(orderRepository.getOne(ORDER_ID_MOCK)).thenReturn(order);
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
