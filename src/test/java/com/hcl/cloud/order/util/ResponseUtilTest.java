package com.hcl.cloud.order.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.cloud.order.dto.CartResponse;
import com.hcl.cloud.order.entity.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.StringUtil;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;

/**
 * This is a test class for ResponseUtil.
 *
 * @author shikhar.a || ankit-kumar
 */

@RunWith(value = PowerMockRunner.class)
@PrepareForTest({ResponseUtil.class, HttpStatus.class})
public class ResponseUtilTest {

    /**
     * responseUtil.
     */
    @InjectMocks
    private ResponseUtil responseUtil;
    /**
     * orderMock.
     */
    @Mock
    private Order orderMock;
    /**
     * SUCCESS.
     */
    private String SUCCESS = "Success";
    /**
     * cartResponse.
     */
    @Mock
    private ResponseEntity<Object> cartResponse;
    /**
     * This Method is called before the test is executed.
     *
     * @throws Exception
     */

    @Before
    public void setUp() throws Exception {
//        this.responseUtil = PowerMockito.spy(new ResponseUtil(orderMock));
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test for Collection.
     */
    @Test
    public void getResponseEntityTestDataSuccess() throws Exception {
        PowerMockito.mockStatic(ResponseUtil.class);
        PowerMockito.mockStatic(HttpStatus.class);
        Object obj = (Object) Collections.EMPTY_LIST;
        Whitebox.invokeMethod(ResponseUtil.class,
                "getResponseEntity",
                HttpStatus.OK,
                SUCCESS,
                obj);
    }

    /**
     * Test for order.
     */
    @Test
    public void getResponseEntityTestCollectionSuccess() throws Exception {
        PowerMockito.mockStatic(ResponseUtil.class);
        PowerMockito.mockStatic(HttpStatus.class);
        Object obj = (Object) orderMock;
        Whitebox.invokeMethod(ResponseUtil.class,
                "getResponseEntity",
                HttpStatus.OK,
                SUCCESS,
                obj);
    }

    /**
     * Test for order.
     */
    @Test
    public void getCartResponseSuccess() throws Exception {
        PowerMockito.mockStatic(ResponseUtil.class);
        PowerMockito.mockStatic(HttpStatus.class);
        ObjectMapper objectMapper = PowerMockito.mock(ObjectMapper.class);
        Object object = Mockito.mock(Object.class);
        JsonNode jsonNode = Mockito.mock(JsonNode.class);
        CartResponse response = Mockito.mock(CartResponse.class);
        Mockito.when(cartResponse.getBody()).thenReturn(object);
        Mockito.when(objectMapper.valueToTree(object)).thenReturn(jsonNode);
        Mockito.when(objectMapper.writeValueAsString(jsonNode)).thenReturn(SUCCESS);
        Mockito.when(objectMapper.readValue(SUCCESS, CartResponse.class)).thenReturn(response);
        Whitebox.invokeMethod(ResponseUtil.class,
                "getCartResponse",
                cartResponse);
    }
}
