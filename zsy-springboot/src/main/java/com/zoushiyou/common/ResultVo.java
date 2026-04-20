package com.zoushiyou.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVo<T> implements Serializable {
    private Integer totalNum = 0;
    private Integer status = 200;
    private String message = "";
    private T data = null;
    
    public ResultVo() {
    }
    
    public ResultVo(Integer totalNum, Integer status, String message, T data) {
        this.totalNum = totalNum;
        this.status = status;
        this.message = message;
        this.data = data;
    }
    
    public static <T> ResultVo<T> success(T data) {
        ResultVo<T> result = new ResultVo<>();
        result.setData(data);
        result.setTotalNum(1);
        return result;
    }
    
    public static <T> ResultVo<T> success(T data, Integer totalNum) {
        ResultVo<T> result = new ResultVo<>();
        result.setData(data);
        result.setTotalNum(totalNum);
        return result;
    }
    
    public static <T> ResultVo<T> error(String message) {
        ResultVo<T> result = new ResultVo<>();
        result.setStatus(500);
        result.setMessage(message);
        return result;
    }
}
