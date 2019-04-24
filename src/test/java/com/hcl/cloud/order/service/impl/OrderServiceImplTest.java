package com.hcl.cloud.order.service.impl;

import com.hcl.cloud.order.entity.Cart;
import com.hcl.cloud.order.entity.Order;
import com.hcl.cloud.order.repository.OrderRepositorySql;
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

import java.util.Collections;
import java.util.Optional;

/*
 * Test class for OrderServiceImpl.
 * @author shikhar.a || ankit-kumar
 * */


@RunWith(value = PowerMockRunner.class)
@PrepareForTest({Optional.class,Order.class})
public class OrderServiceImplTest {

    /**
     *
     */
    private final String ORDER_ID = "ORDER_ID";

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
    public final void testCheckoutSuccess() {
        PowerMockito.mockStatic(Order.class);
        Mockito.when(Order.getSampleOrder()).thenReturn(order);
        PowerMockito.when(orderRepository.save(order)).thenReturn(order);
        orderServiceImpl.checkout(cartMock);
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
