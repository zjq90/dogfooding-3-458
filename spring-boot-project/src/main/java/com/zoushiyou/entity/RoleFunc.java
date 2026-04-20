package com.zoushiyou.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleFunc extends BaseEntity {
    private Long roleId;
    private Long funcId;
    private Integer levelVal;
}
