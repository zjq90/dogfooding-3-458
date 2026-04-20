package com.zoushiyou.dto;

import com.zoushiyou.entity.FuncInfo;
import lombok.Data;

import java.util.List;

@Data
public class FuncInfoDto {
    private Long id;
    private String code;
    private String name;
    private String routePath;
    private String styleName;
    private Integer levelVal;
    private List<FuncInfoDto> subItem;
    
    public FuncInfoDto() {
    }
    
    public FuncInfoDto(FuncInfo funcInfo) {
        this.id = funcInfo.getId();
        this.code = funcInfo.getCode();
        this.name = funcInfo.getName();
        this.routePath = funcInfo.getRoutePath();
        this.styleName = funcInfo.getStyleName();
        this.levelVal = funcInfo.getLevelVal();
    }
}
