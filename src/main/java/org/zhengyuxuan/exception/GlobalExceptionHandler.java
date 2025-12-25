package org.zhengyuxuan.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zhengyuxuan.vo.ResultVO;

/**
 * 全局异常处理器
 * 统一处理控制器抛出的异常
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理业务异常
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResultVO<Void> handleBusinessException(BusinessException e) {
        logger.warn("业务异常: {}", e.getMessage());
        return ResultVO.error(e.getCode(), e.getMessage());
    }

    /**
     * 处理参数异常
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResultVO<Void> handleIllegalArgumentException(IllegalArgumentException e) {
        logger.warn("参数异常: {}", e.getMessage());
        return ResultVO.error("参数错误: " + e.getMessage());
    }

    /**
     * 处理其他异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultVO<Void> handleException(Exception e) {
        logger.error("系统异常: ", e);
        return ResultVO.error("系统繁忙，请稍后重试");
    }
}

