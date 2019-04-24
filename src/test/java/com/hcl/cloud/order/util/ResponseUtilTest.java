package com.hcl.cloud.order.util;

import com.hcl.cloud.order.entity.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpStatus;
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

    private ResponseUtil responseUtil;

    @Mock
    private Order orderMock;

    private String SUCCESS = "Success";

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
    public void getResponseEntityTestDataSuccess() {
        PowerMockito.mockStatic(ResponseUtil.class);
        PowerMockito.mockStatic(HttpStatus.class);
        ResponseUtil.getResponseEntity(HttpStatus.OK, SUCCESS, Collections.EMPTY_LIST);
    }

    /**
     * Test for order.
     */
    @Test
    public void getResponseEntityTestCollectionSuccess() {
        PowerMockito.mockStatic(ResponseUtil.class);
        PowerMockito.mockStatic(HttpStatus.class);
        ResponseUtil.getResponseEntity(HttpStatus.OK, SUCCESS, orderMock);
    }
}

