package com.zoushiyou.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Func extends BaseEntity {
    private String routePath;
    private String styleName;
    private Integer levelVal;
}
