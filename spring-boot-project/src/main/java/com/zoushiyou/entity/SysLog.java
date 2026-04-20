package com.zoushiyou.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysLog extends BaseEntity {
    private Integer opererType;
    private String opererContent;
}
