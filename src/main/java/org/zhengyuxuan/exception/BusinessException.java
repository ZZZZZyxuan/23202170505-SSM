package org.zhengyuxuan.exception;

/**
 * 业务异常类
 * 用于表示业务逻辑中的可预期异常
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /** 错误码 */
    private Integer code;

    public BusinessException(String message) {
        super(message);
        this.code = 500;
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

