package com.zoushiyou.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户登录信息DTO
 */
@Data
public class UserLoginDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户信息
     */
    private UserInfoDto userInfo;

    /**
     * 角色信息
     */
    private CommonDto roleInfo;

    /**
     * 部门信息
     */
    private CommonDto deptInfo;

    /**
     * 功能菜单列表
     */
    private List<FuncInfoDto> funcList;
}
