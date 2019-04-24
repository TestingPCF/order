package com.hcl.cloud.order.controller;

import com.hcl.cloud.order.constant.OrderConstant;
import com.hcl.cloud.order.entity.Cart;
import com.hcl.cloud.order.entity.Order;
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
     * ORDER_ID.
     */
    private static final Long ORDER_ID = 1l;
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
     * ResponseEntity Mock.
     */

    @Mock
    private ResponseEntity responseEntity;
    /**
     * ACCESS_TOKEN.
     */
    private String ACCESS_TOKEN = "TOKEN";

    /**
     * orderListMock.
     */
    private List<Order> orderListMock = new ArrayList<>();


    /**
     * This is a method for preprocessing tasks.
     *
     * @throws Exception Exception.
     */

    @Before
    public void setUp() throws Exception {
        orderController = PowerMockito.spy(new OrderController());
        MockitoAnnotations.initMocks(this);

        PowerMockito.mockStatic(ResponseUtil.class);
        PowerMockito.when(ResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR
                , OrderConstant.ORDER_FAILED, null)).thenReturn(responseEntity);
    }


    /**
     * Test Success for create Order.
     */

    @Test
    public final void testCreateOrderSuccess() {
        PowerMockito.mockStatic(Order.class);
        Mockito.when(Order.getSampleOrder()).thenReturn(orderMock);
        Mockito.when(orderService.checkout(cartMock)).thenReturn(orderMock);
        orderController.createOrder(cartMock);
    }

    /**
     * Failure test for create order.
     *
     * @throws Exception
     */
    @Test(expected = Exception.class)
    public final void testCreateOrderFail() throws Exception {
        PowerMockito.mockStatic(Order.class);
        Mockito.when(Order.getSampleOrder()).thenReturn(orderMock);
        Mockito.when(orderService.checkout(cartMock)).thenThrow(Exception.class);
        Assert.assertEquals(HttpStatus.CREATED, orderController.createOrder(cartMock).getStatusCode());
    }

    /**
     * Test Success for create Order.
     */

    @Test
    public final void testGetOrderSuccess() {
        Mockito.when(orderService.getOrder(ORDER_ID)).thenReturn(orderMock);
        orderController.getOrder(ORDER_ID, ACCESS_TOKEN);
    }

    /**
     * Failure test for get order.
     *
     * @throws Exception
     */
    @Test(expected = Exception.class)
    public final void testGetOrderFail() throws Exception {
        Mockito.when(orderService.getOrder(ORDER_ID)).thenThrow(Exception.class);
        orderController.getOrder(ORDER_ID, ACCESS_TOKEN);
    }

    /**
     * Failure test for get order.
     *
     * @throws Exception
     */
    @Test(expected = Exception.class)
    public final void testGetOrderOtherFail() throws Exception {
        Mockito.when(orderService.getOrder(ORDER_ID)).thenReturn(orderMock);
        PowerMockito.mockStatic(ResponseUtil.class);
        PowerMockito.when(ResponseUtil.getResponseEntity(HttpStatus.OK
                , OrderConstant.ORDER_SUCCESS, orderMock)).thenThrow(Exception.class);
        orderController.getOrder(ORDER_ID, ACCESS_TOKEN);
    }

    /**
     * Test Success for get all Order.
     */

    @Test
    public final void testAllOrderSuccess() {
        PowerMockito.mockStatic(Order.class);
        Mockito.when(Order.getSampleOrder()).thenReturn(orderMock);
        Mockito.when(orderService.getAllOrders()).thenReturn(orderListMock);
        orderController.getAllOrders(ACCESS_TOKEN);
    }

    /**
     * Failure test for get all order.
     *
     * @throws Exception
     */
    @Test(expected = Exception.class)
    public final void testAllOrderFail() throws Exception {
        PowerMockito.mockStatic(Order.class);
        Mockito.when(Order.getSampleOrder()).thenReturn(orderMock);
        Mockito.when(orderService.getAllOrders()).thenThrow(Exception.class);
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
        orderController.updateOrder(orderMock);
    }

    /**
     * Failure test for update order.
     *
     * @throws Exception
     */
    @Test(expected = Exception.class)
    public final void testUpdateOrderFail() throws Exception {
        PowerMockito.mockStatic(Order.class);
        Mockito.when(orderMock.getOrderId()).thenReturn(ORDER_ID);
        Mockito.when(Order.getSampleOrder()).thenReturn(orderMock);
        Mockito.when(orderService.updateOrder(orderMock)).thenThrow(Exception.class);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, orderController.updateOrder(orderMock).getStatusCode());
    }

}

