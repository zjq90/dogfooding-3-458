package com.zoushiyou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zoushiyou.entity.RoleInfo;
import com.zoushiyou.mapper.RoleInfoMapper;
import com.zoushiyou.service.RoleInfoService;
import org.springframework.stereotype.Service;

@Service
public class RoleInfoServiceImpl extends ServiceImpl<RoleInfoMapper, RoleInfo> implements RoleInfoService {
}
