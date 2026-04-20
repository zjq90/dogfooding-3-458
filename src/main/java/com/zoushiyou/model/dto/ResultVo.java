package com.zoushiyou.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一响应结果
 */
@Data
public class ResultVo<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码 200成功 500失败
     */
    private Integer status = 200;

    /**
     * 消息
     */
    private String message = "操作成功";

    /**
     * 数据
     */
    private T data;

    /**
     * 总记录数
     */
    private Integer totalNum = 0;

    public ResultVo() {
    }

    public ResultVo(T data) {
        this.data = data;
    }

    public ResultVo(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public static <T> ResultVo<T> success() {
        return new ResultVo<>();
    }

    public static <T> ResultVo<T> success(T data) {
        ResultVo<T> result = new ResultVo<>();
        result.setData(data);
        return result;
    }

    public static <T> ResultVo<T> success(T data, Integer totalNum) {
        ResultVo<T> result = new ResultVo<>();
        result.setData(data);
        result.setTotalNum(totalNum);
        return result;
    }

    public static <T> ResultVo<T> error(String message) {
        return new ResultVo<>(500, message);
    }

    public static <T> ResultVo<T> error(Integer status, String message) {
        return new ResultVo<>(status, message);
    }
}
