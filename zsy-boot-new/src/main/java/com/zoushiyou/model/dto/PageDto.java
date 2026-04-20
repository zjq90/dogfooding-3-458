package com.zoushiyou.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页数据传输对象
 */
@Data
public class PageDto<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    private Integer pageIndex = 1;

    /**
     * 每页大小
     */
    private Integer pageSize = 10;

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 总页数
     */
    private Long pages;

    /**
     * 数据列表
     */
    private List<T> records;

    public PageDto() {
    }

    public PageDto(Integer pageIndex, Integer pageSize, Long total, List<T> records) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.total = total;
        this.records = records;
        this.pages = (total + pageSize - 1) / pageSize;
    }
}
