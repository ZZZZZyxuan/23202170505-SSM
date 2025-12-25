package org.zhengyuxuan.vo;

/**
 * 统一响应结果类
 * @param <T> 数据类型
 */
public class ResultVO<T> {

    /** 状态码 */
    private Integer code;

    /** 提示信息 */
    private String message;

    /** 数据 */
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

    /**
     * 成功返回结果
     */
    public static <T> ResultVO<T> success() {
        return new ResultVO<>(200, "操作成功");
    }

    /**
     * 成功返回结果（带数据）
     */
    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<>(200, "操作成功", data);
    }

    /**
     * 成功返回结果（带消息和数据）
     */
    public static <T> ResultVO<T> success(String message, T data) {
        return new ResultVO<>(200, message, data);
    }

    /**
     * 失败返回结果
     */
    public static <T> ResultVO<T> error(String message) {
        return new ResultVO<>(500, message);
    }

    /**
     * 失败返回结果（带状态码）
     */
    public static <T> ResultVO<T> error(Integer code, String message) {
        return new ResultVO<>(code, message);
    }

    /**
     * 未登录返回结果
     */
    public static <T> ResultVO<T> unauthorized() {
        return new ResultVO<>(401, "请先登录");
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

