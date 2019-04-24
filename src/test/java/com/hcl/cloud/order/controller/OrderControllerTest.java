package com.hcl.cloud.order.controller;

import com.hcl.cloud.order.entity.Cart;
import com.hcl.cloud.order.entity.Order;
import com.hcl.cloud.order.entity.Response;
import com.hcl.cloud.order.entity.Status;
import com.hcl.cloud.order.service.OrderService;
import com.hcl.cloud.order.util.ResponseUtil;
import org.junit.Assert;
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

import java.util.ArrayList;
import java.util.List;

/**
 * This is a test class for OrderController
 *
 * @author shikhar.a || ankit-kumar
 */

@RunWith(value = PowerMockRunner.class)
@PrepareForTest({ResponseUtil.class, HttpStatus.class, Order.class})
public class OrderControllerTest {

    /**
     * OrderController mock.
     */

    @InjectMocks
    private OrderController orderController;


    /**
     * OrderService mock.
     */

    @Mock
    private OrderService orderService;


    /**
     * Order Mock.
     */

    @Mock
    private Order orderMock;


    /**
     * Cart Mock.
     */

    @Mock
    private Cart cartMock;

    /**
     * ORDER_ID.
     */
    private static final Long ORDER_ID = 1l;

    /**
     * ACCESS_TOKEN.
     */
    private String ACCESS_TOKEN = "TOKEN";

    /**
     * orderListMock.
     */
    private List<Order> orderListMock  = new ArrayList<>();


    /**
     * This is a method for preprocessing tasks.
     *
     * @throws Exception Exception.
     */

    @Before
    public void setUp() throws Exception {
        orderController = PowerMockito.spy(new OrderController());
        MockitoAnnotations.initMocks(this);
    }


    /**
     * Test Success for create Order.
     */

    @Test
    public final void testCreateOrderSuccess() {
        PowerMockito.mockStatic(Order.class);
        Mockito.when(Order.getSampleOrder()).thenReturn(orderMock);
        Mockito.when(orderService.checkout(cartMock)).thenReturn(orderMock);
        Assert.assertEquals(HttpStatus.CREATED, orderController.createOrder(cartMock).getStatusCode());
    }


    private static final String TEST_STRING = "TEST_STRING";

    /**
     * Failure test for create order.
     * @throws Exception
     */
    @Test
    public final void testCreateOrderFail() throws  Exception {
        Status status = new Status(HttpStatus.INTERNAL_SERVER_ERROR, TEST_STRING);
        Response response = new Response.Builder<Order>(status).build();
        ResponseEntity<Response<Order>> responseEntityMock =
                new ResponseEntity<Response<Order>>(response,
                        HttpStatus.INTERNAL_SERVER_ERROR);
        Mockito.when(orderService.checkout(cartMock)).thenReturn(orderMock);
        orderMock.setOrderId(ORDER_ID);
        Mockito.when(orderController.createOrder(cartMock)).thenThrow(RuntimeException.class);
        Assert.assertEquals(responseEntityMock.getStatusCode(),
                orderController.createOrder(cartMock).getStatusCode());
    }


    /**
     * Test Success for create Order.
     */

    @Test
    public final void testGetOrderSuccess() {
        PowerMockito.mockStatic(Order.class);
        Mockito.when(Order.getSampleOrder()).thenReturn(orderMock);
        Mockito.when(orderService.getOrder(ORDER_ID)).thenReturn(orderMock);
        Assert.assertEquals(HttpStatus.OK, orderController.getOrder(ORDER_ID,ACCESS_TOKEN).getStatusCode());
    }

    /**
     * Failure test for create order.
     * @throws Exception
     */
    @Test
    public final void testGetOrderFail() throws  Exception {
        Status status = new Status(HttpStatus.INTERNAL_SERVER_ERROR, TEST_STRING);
        Response response = new Response.Builder<Order>(status).build();
        ResponseEntity<Response<Order>> responseEntityMock =
                new ResponseEntity<Response<Order>>(response,
                        HttpStatus.INTERNAL_SERVER_ERROR);
        Mockito.when(orderService.getOrder(ORDER_ID)).thenReturn(orderMock);
        orderMock.setOrderId(ORDER_ID);
        Mockito.when(orderController.getOrder(ORDER_ID, ACCESS_TOKEN)).thenThrow(RuntimeException.class);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
                orderController.getOrder(ORDER_ID, ACCESS_TOKEN).getStatusCode());
    }

    /**
     * Test Success for get all Order.
     */

    @Test
    public final void testAllOrderSuccess() {
        PowerMockito.mockStatic(Order.class);
        Mockito.when(Order.getSampleOrder()).thenReturn(orderMock);
        Mockito.when(orderService.getAllOrders()).thenReturn(orderListMock);
        Assert.assertEquals(HttpStatus.OK, orderController.getAllOrders(ACCESS_TOKEN).getStatusCode());
    }

    /**
     * Failure test for get all order.
     * @throws Exception
     */
    @Test
    public final void testAllOrderFail() throws  Exception {
        Status status = new Status(HttpStatus.INTERNAL_SERVER_ERROR, TEST_STRING);
        Response response = new Response.Builder<Order>(status).build();
        ResponseEntity<Response<Order>> responseEntityMock =
                new ResponseEntity<Response<Order>>(response,
                        HttpStatus.INTERNAL_SERVER_ERROR);
        Mockito.when(orderService.getAllOrders()).thenReturn(orderListMock);
        Mockito.when(orderController.getAllOrders(ACCESS_TOKEN)).thenThrow(RuntimeException.class);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, orderController.getAllOrders(ACCESS_TOKEN).getStatusCode());
    }

    /**
     * Test Success for update Order.
     */

    @Test
    public final void testUpdateOrderSuccess() {
        PowerMockito.mockStatic(Order.class);
        Mockito.when(Order.getSampleOrder()).thenReturn(orderMock);
        Mockito.when(orderService.getOrder(ORDER_ID)).thenReturn(orderMock);
        orderMock.setOrderId(ORDER_ID);
        Mockito.when(orderService.updateOrder(orderMock)).thenReturn(orderMock);
        Assert.assertEquals(HttpStatus.OK, orderController.updateOrder(orderMock).getStatusCode());
    }

    /**
     * Failure test for update order.
     * @throws Exception
     */
    @Test(expected = Exception.class)
    public final void testUpdateOrderFail() throws  Exception {
        PowerMockito.mockStatic(Order.class);
        Mockito.when(orderMock.getOrderId()).thenReturn(ORDER_ID);
        Mockito.when(Order.getSampleOrder()).thenReturn(orderMock);
        Mockito.when(orderService.updateOrder(orderMock)).thenThrow(Exception.class);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, orderController.updateOrder(orderMock).getStatusCode());
    }

}

