package com.hcl.cloud.order.config;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;

@RunWith(value = PowerMockRunner.class)
public class SwaggerConfigTest {
    /**
     * SwaggerConfigTest mock.
     */
    @InjectMocks
    private SwaggerConfig swaggerConfigMock;

    /**
     * This is a method for preprocessing tasks.
     *
     * @throws Exception Exception.
     */
    @Before
    public void setUp() throws Exception {
        swaggerConfigMock = PowerMockito.spy(new SwaggerConfig());
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test Success for postsApi.
     */
    @Test
    public final void testPostsApiSuccess() throws IOException {
        swaggerConfigMock.postsApi();
    }
}
