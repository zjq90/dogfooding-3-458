package com.zoushiyou.exception;

import com.zoushiyou.model.dto.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public ResultVo<Void> handleBusinessException(BusinessException e, HttpServletRequest request) {
        log.error("业务异常，请求路径：{}，错误信息：{}", request.getRequestURI(), e.getMessage());
        return ResultVo.error(e.getMessage());
    }

    /**
     * 处理参数校验异常
     */
    @ExceptionHandler(BindException.class)
    public ResultVo<Void> handleBindException(BindException e, HttpServletRequest request) {
        String message = e.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        log.error("参数校验失败，请求路径：{}，错误信息：{}", request.getRequestURI(), message);
        return ResultVo.error(message);
    }

    /**
     * 处理其他异常
     */
    @ExceptionHandler(Exception.class)
    public ResultVo<Void> handleException(Exception e, HttpServletRequest request) {
        log.error("系统异常，请求路径：{}，错误信息：{}", request.getRequestURI(), e.getMessage(), e);
        return ResultVo.error("系统繁忙，请稍后重试");
    }
}
