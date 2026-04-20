package com.zoushiyou.dto;

import com.zoushiyou.entity.BaseEntity;
import lombok.Data;

@Data
public class CommonDto {
    private Long id;
    private String code;
    private String name;
    
    public CommonDto() {
    }
    
    public CommonDto(BaseEntity entity) {
        this.id = entity.getId();
        this.code = entity.getCode();
        this.name = entity.getName();
    }
}
