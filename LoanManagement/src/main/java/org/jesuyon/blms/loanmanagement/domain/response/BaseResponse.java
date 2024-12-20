package org.jesuyon.blms.loanmanagement.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

public class BaseResponse <T>{
    private String message;
    private T data;
    private int responseCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Meta meta;

    public BaseResponse(String message) {
        this(message, null, HttpStatus.OK.value(), null);
    }

    public BaseResponse(T data) {
        this(data, null);
    }

    public BaseResponse(T data, int statusCode) {
        this(data, null);
    }

    public BaseResponse(String message, T data) {
        this(message, data, HttpStatus.OK.value(), null);
    }

    public BaseResponse(T data, Meta meta) {
        this("Operation Successful",  data, HttpStatus.OK.value(), meta);
    }

    public BaseResponse(String message, T data,int responseCode, Meta meta) {
        this.message = message;
        this.data = data;
        this.responseCode =responseCode;
        this.meta = meta;
    }

    public BaseResponse(String message, T data,int responseCode) {
        this.message = message;
        this.data = data;
        this.responseCode =responseCode;
        this.meta = null;
    }

    public String getMessage() {
        return message;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public T getData() {
        return data;
    }

    public Meta getMeta() {
        return meta;
    }

    public record Meta(long currentPage, long from, long to, long perPage, long total, long lastPage) { }
}
