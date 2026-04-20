package com.zoushiyou.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户信息实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_userinfo")
public class UserInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 密码
     */
    @JsonIgnore
    private String passWord;

    /**
     * md5密码盐
     */
    @JsonIgnore
    private String salt;

    /**
     * 性别 1：男 0：女
     */
    private Integer isMale;

    /**
     * 手机号码
     */
    private String phoneNum;

    /**
     * 用户角色Id
     */
    private Long roleId;

    /**
     * 部门Id
     */
    private Long deptId;
}
