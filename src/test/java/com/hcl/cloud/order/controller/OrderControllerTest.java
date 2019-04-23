/*
package com.hcl.cloud.order.controller;

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

*/
/**
 * This is a test class for OrderController
 * @author shikhar.a || ankit-kumar
 *//*

@RunWith(value = PowerMockRunner.class)
@PrepareForTest({ResponseUtil.class, HttpStatus.class, Order.class})
public class OrderControllerTest {
    */
/**
     * OrderController mock.
     *//*

    @InjectMocks
    private OrderController orderController;

    */
/**
     * OrderService mock.
     *//*

    @Mock
    private OrderService orderService;

    */
/**
     * Order Mock.
     *//*

    @Mock
    private Order orderMock;

    */
/**
     * Cart Mock.
     *//*

    @Mock
    private Cart cartMock;

    */
/**
     * This is a method for preprocessing tasks.
     * @throws Exception Exception.
     *//*

    @Before
    public void setUp() throws Exception {
        orderController = PowerMockito.spy(new OrderController());
        MockitoAnnotations.initMocks(this);
    }

    */
/**
     * Test Success for create Order.
     *
     *//*

    @Test
    public final void testCreateOrderSuccess() {
        PowerMockito.mockStatic(Order.class);
        Mockito.when(Order.getSampleOrder()).thenReturn(orderMock);
        Mockito.when(orderService.checkout(orderMock)).thenReturn(orderMock);
        Assert.assertEquals(HttpStatus.CREATED, orderController.createOrder(cartMock).getStatusCode());
    }

}
*/
