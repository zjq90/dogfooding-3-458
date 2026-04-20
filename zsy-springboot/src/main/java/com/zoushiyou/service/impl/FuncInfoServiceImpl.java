package com.zoushiyou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zoushiyou.entity.FuncInfo;
import com.zoushiyou.mapper.FuncInfoMapper;
import com.zoushiyou.service.FuncInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncInfoServiceImpl extends ServiceImpl<FuncInfoMapper, FuncInfo> implements FuncInfoService {
    
    @Override
    public List<FuncInfo> findByRoleId(Long roleId) {
        return baseMapper.findByRoleId(roleId);
    }
}
