package com.sep.PaymentConcentrator.dto;


import java.math.BigDecimal;
import java.sql.Timestamp;

public class MerchantDTO {

    private String merchantId;
    private String merchantPassword;
    private BigDecimal amount;
    private Integer merchantOrderID;
    private Timestamp merchantTimestamp;
    private String errorUrl;
    private long tenantID;

    public long getTenantID() {
		return tenantID;
	}

	public void setTenantID(long tenantID) {
		this.tenantID = tenantID;
	}

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantPassword() {
        return merchantPassword;
    }

    public void setMerchantPassword(String merchantPassword) {
        this.merchantPassword = merchantPassword;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getMerchantOrderID() {
        return merchantOrderID;
    }

    public void setMerchantOrderID(Integer merchantOrderID) {
        this.merchantOrderID = merchantOrderID;
    }

    public Timestamp getMerchantTimestamp() {
        return merchantTimestamp;
    }

    public void setMerchantTimestamp(Timestamp merchantTimestamp) {
        this.merchantTimestamp = merchantTimestamp;
    }

    public String getErrorUrl() {
        return errorUrl;
    }

    public void setErrorUrl(String errorUrl) {
        this.errorUrl = errorUrl;
    }
}
