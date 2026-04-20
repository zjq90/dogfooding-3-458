package com.zoushiyou.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色功能关联实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_rolejoinfunc")
public class RoleJoinFunc extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色Id
     */
    private Long roleId;

    /**
     * 功能Id
     */
    private Long funcId;

    /**
     * 级别值域
     */
    private Integer levelVal;
}
