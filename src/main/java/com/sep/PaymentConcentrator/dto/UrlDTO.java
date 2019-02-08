package com.sep.PaymentConcentrator.dto;

import com.sep.PaymentConcentrator.dto.ResponseMessageDTO.TransactionResult;

public class UrlDTO {
	
	public enum Status {
        SUCCESSFUL, FAILED, ERROR
    }

    private String url;
    private Status status;
    private TransactionResult result;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public TransactionResult getResult() {
        return result;
    }

    public void setResult(TransactionResult result) {
        this.result = result;
    }

}
