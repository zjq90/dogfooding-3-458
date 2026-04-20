package com.zoushiyou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zoushiyou.entity.FuncInfo;

import java.util.List;

public interface FuncInfoService extends IService<FuncInfo> {
    List<FuncInfo> findByRoleId(Long roleId);
}
