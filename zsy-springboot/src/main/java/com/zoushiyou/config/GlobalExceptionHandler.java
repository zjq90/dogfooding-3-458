package com.zoushiyou.config;

import com.zoushiyou.common.ResultVo;
import com.zoushiyou.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultVo<Void> handleBusinessException(HttpServletRequest request, BusinessException e) {
        logger.error("Business error at {}: {}", request.getRequestURI(), e.getMessage());
        ResultVo<Void> result = new ResultVo<>();
        result.setStatus(500);
        result.setMessage(e.getMessage());
        return result;
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVo<Void> handleException(HttpServletRequest request, Exception e) {
        logger.error("System error at {}: ", request.getRequestURI(), e);
        ResultVo<Void> result = new ResultVo<>();
        result.setStatus(500);
        result.setMessage(e.getMessage());
        return result;
    }
}
