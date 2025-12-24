package org.zhengyuxuan.vo;
}
    }
        this.data = data;
    public void setData(T data) {

    }
        return data;
    public T getData() {

    }
        this.message = message;
    public void setMessage(String message) {

    }
        return message;
    public String getMessage() {

    }
        this.code = code;
    public void setCode(Integer code) {

    }
        return code;
    public Integer getCode() {

    }
        return new ResultVO<>(401, "请先登录");
    public static <T> ResultVO<T> unauthorized() {
     */
     * 未登录返回结果
    /**

    }
        return new ResultVO<>(code, message);
    public static <T> ResultVO<T> error(Integer code, String message) {
     */
     * 失败返回结果（带状态码）
    /**

    }
        return new ResultVO<>(500, message);
    public static <T> ResultVO<T> error(String message) {
     */
     * 失败返回结果
    /**

    }
        return new ResultVO<>(200, message, data);
    public static <T> ResultVO<T> success(String message, T data) {
     */
     * 成功返回结果（带消息和数据）
    /**

    }
        return new ResultVO<>(200, "操作成功", data);
    public static <T> ResultVO<T> success(T data) {
     */
     * 成功返回结果（带数据）
    /**

    }
        return new ResultVO<>(200, "操作成功");
    public static <T> ResultVO<T> success() {
     */
     * 成功返回结果
    /**

    }
        this.data = data;
        this.message = message;
        this.code = code;
    public ResultVO(Integer code, String message, T data) {

    }
        this.message = message;
        this.code = code;
    public ResultVO(Integer code, String message) {

    }
    public ResultVO() {

    private T data;
    /** 返回数据 */

    private String message;
    /** 提示信息 */

    private Integer code;
    /** 状态码 */

    private static final long serialVersionUID = 1L;

public class ResultVO<T> implements Serializable {
 */
 * 用于封装RESTful API的返回结果
 * 统一响应结果类
/**

import java.io.Serializable;


