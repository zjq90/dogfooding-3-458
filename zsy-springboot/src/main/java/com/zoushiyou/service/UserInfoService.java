package com.zoushiyou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zoushiyou.entity.UserInfo;
import com.zoushiyou.dto.UserLoginInfoDto;

public interface UserInfoService extends IService<UserInfo> {
    UserInfo findByUserName(String username);
    UserLoginInfoDto buildLoginData(UserInfo userInfo, String token);
}
