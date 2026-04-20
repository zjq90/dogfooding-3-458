package com.zoushiyou.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    private String passWord;
    private String salt;
    private Integer isMale;
    private String phoneNum;
    private Long roleId;
    private Long deptId;
}
