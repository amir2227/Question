package com.mvp.question.payload.response;

public class MessageResponse {
    private String message;
    private Boolean hasError;
    private Integer status;
    private Object data;

    public MessageResponse() {
    }

    public MessageResponse(String message, Boolean hasError, Integer status, Object data) {
        this.message = message;
        this.hasError = hasError;
        this.status = status;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getHasError() {
        return hasError;
    }

    public void setHasError(Boolean hasError) {
        this.hasError = hasError;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
