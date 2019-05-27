package com.hcl.cloud.order.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * This is a configuration class which will read properties from config
 * server depending upon the activated profile.
 * @author shikhar.a
 */
@Configuration
@RefreshScope
public class ConstantConfig {
    /**
     * Constant for successRetrieve.
     */
    private String successRetrieve;
    
    private String successUpdate;

    public String getSuccessUpdate() {
		return successUpdate;
	}

	public void setSuccessUpdate(String successUpdate) {
		this.successUpdate = successUpdate;
	}

	/**
     * Getter of successRetrieve.
     * @return successRetrieve successRetrieve
     */
    public String getSuccessRetrieve() {
        return successRetrieve;
    }

    /**
     * Setter of successRetrieve.
     * @param successRetrieve successRetrieve
     */
    @Value("${order.request.success.message}")
    public void setSuccessRetrieve(String successRetrieve) {
        this.successRetrieve = successRetrieve;
    }
}
