package com.zoushiyou.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色信息实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_roleinfo")
public class RoleInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;
}
