package com.zoushiyou.dto;

import lombok.Data;

@Data
public class UserLoginInfoDto {
    public UserInfoDto userInfoDto;
    public CommonDto roleInfoDto;
    public CommonDto deptInfoDto;
    public java.util.List<FuncInfoDto> listfuncInfoDto;
}
