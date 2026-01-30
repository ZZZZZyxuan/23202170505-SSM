package org.zhengyuxuan.entity;

import org.zhengyuxuan.service.AppConstants;

public class ResultVO<T> {

    private Integer code;

    private String message;

    private T data;

    public ResultVO() {
    }

    public ResultVO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultVO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResultVO<T> success() { // 成功响应(无数据)
        return new ResultVO<>(AppConstants.CODE_SUCCESS, AppConstants.MSG_SUCCESS);
    }

    public static <T> ResultVO<T> success(T data) { // 成功响应(带数据)
        return new ResultVO<>(AppConstants.CODE_SUCCESS, AppConstants.MSG_SUCCESS, data);
    }

    public static <T> ResultVO<T> success(String message, T data) { // 成功响应(自定义消息)
        return new ResultVO<>(AppConstants.CODE_SUCCESS, message, data);
    }

    public static <T> ResultVO<T> error(String message) { // 错误响应
        return new ResultVO<>(AppConstants.CODE_ERROR, message);
    }

    public static <T> ResultVO<T> error(Integer code, String message) { // 错误响应(自定义状态码)
        return new ResultVO<>(code, message);
    }

    public static <T> ResultVO<T> unauthorized() { // 未授权响应
        return new ResultVO<>(AppConstants.CODE_UNAUTHORIZED, AppConstants.MSG_UNAUTHORIZED);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

