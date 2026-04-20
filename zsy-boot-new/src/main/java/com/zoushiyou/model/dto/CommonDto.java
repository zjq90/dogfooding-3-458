package com.zoushiyou.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用DTO
 */
@Data
public class CommonDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String code;
    private String name;
    private String remarks;

    public CommonDto() {
    }

    public CommonDto(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }
}
