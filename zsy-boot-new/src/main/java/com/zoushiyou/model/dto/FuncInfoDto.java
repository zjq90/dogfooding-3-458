package com.zoushiyou.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 功能菜单DTO
 */
@Data
public class FuncInfoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String code;
    private String name;
    private String routePath;
    private String styleName;
    private Integer levelVal;
    private Integer sortNum;
    private List<FuncInfoDto> subItems;
}
