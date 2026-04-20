package com.zoushiyou.dto;

import com.zoushiyou.entity.UserInfo;
import lombok.Data;

@Data
public class UserInfoDto {
    private Long id;
    private String code;
    private String name;
    private String phoneNum;
    private Long roleId;
    private Long deptId;
    private Integer isMale;
    private String token;
    
    public UserInfoDto() {
    }
    
    public UserInfoDto(UserInfo userInfo) {
        this.id = userInfo.getId();
        this.code = userInfo.getCode();
        this.name = userInfo.getName();
        this.phoneNum = userInfo.getPhoneNum();
        this.roleId = userInfo.getRoleId();
        this.deptId = userInfo.getDeptId();
        this.isMale = userInfo.getIsMale();
    }
}
