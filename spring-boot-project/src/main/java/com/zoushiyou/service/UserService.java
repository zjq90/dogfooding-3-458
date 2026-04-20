package com.zoushiyou.service;

import com.github.pagehelper.PageInfo;
import com.zoushiyou.dto.LoginResultDTO;
import com.zoushiyou.dto.PageQueryDTO;
import com.zoushiyou.dto.UserLoginDTO;
import com.zoushiyou.entity.User;

public interface UserService {
    LoginResultDTO login(UserLoginDTO loginDTO);

    User getById(Long id);

    PageInfo<User> getPage(PageQueryDTO queryDTO);

    boolean save(User user);

    boolean update(User user);

    boolean delete(Long id, Long operatorId);
}
