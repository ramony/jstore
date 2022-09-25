package com.raybyte.jstore.entity;

import lombok.Data;

@Data
public class Result<T> {
    private boolean success = false;
    private T data;
    private String errorCode;
    private String errorMsg;

    private Result() {

    }

    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.success = true;
        result.data = data;
        return result;
    }

    public static <T> Result<T> fail(String errorCode, String errorMsg) {
        Result<T> result = new Result<>();
        result.errorCode = errorCode;
        result.errorMsg = errorMsg;
        return result;
    }

}
