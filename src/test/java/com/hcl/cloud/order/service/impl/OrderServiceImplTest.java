/*
package com.hcl.cloud.order.service.impl;

import com.hcl.cloud.order.entity.Order;
import com.hcl.cloud.order.repository.OrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Collections;
import java.util.Optional;

*/
/**
 * Test class for OrderServiceImpl.
 * @author shikhar.a || ankit-kumar
 *//*

@RunWith(value = PowerMockRunner.class)
@PrepareForTest({ Optional.class })
public class OrderServiceImplTest {

    */
/**
     * OrderServiceImpl Mock.
     *//*

    @InjectMocks
    private OrderServiceImpl orderServiceImpl;


    */
/**
     * OrderRepository Mock.
     *//*

    @Mock
    private OrderRepository orderRepository;

    */
/**
     * Order Mock.
     *//*

    @Mock
    private Order order;

    private final String ORDER_ID = "ORDER_ID";

    */
/**
     * This Method is called before the test is executed.
     * @throws Exception
     *//*

    @Before
    public void setUp() throws Exception {
        this.orderServiceImpl = PowerMockito.spy(new OrderServiceImpl());
        MockitoAnnotations.initMocks(this);
    }

    */
/**
     * Success Test for checkout Method.
     *//*

    @Test
    public final void testCheckoutSuccess() {
        PowerMockito.when(orderRepository.save(order)).thenReturn(order);
        orderServiceImpl.checkout(order);
    }

    */
/**
     * Success Test for updateOrder Method.
     *//*

    @Test
    public final void testUpdateOrderSuccess() {
        PowerMockito.when(orderRepository.save(order)).thenReturn(order);
        orderServiceImpl.updateOrder(order);
    }

    */
/**
     * Success Test for getAllOrders Method.
     *//*

    @Test
    public final void testGetAllOrdersSuccess() {
        PowerMockito.when(orderRepository.findAll()).thenReturn(Collections.emptyList());
        orderServiceImpl.getAllOrders();
    }
}
*/
