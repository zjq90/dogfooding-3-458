package com.zoushiyou.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_rolejoinfunc")
public class RoleJoinFunc extends BaseEntity {
    private Long roleId;
    private Long funcId;
    private Integer levelVal;
}
