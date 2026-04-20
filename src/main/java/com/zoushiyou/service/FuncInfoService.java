package com.zoushiyou.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zoushiyou.mapper.FuncInfoMapper;
import com.zoushiyou.model.entity.FuncInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能菜单服务类
 */
@Service
public class FuncInfoService extends ServiceImpl<FuncInfoMapper, FuncInfo> {

    /**
     * 根据角色ID查询功能列表
     */
    public List<FuncInfo> findByRoleId(Long roleId) {
        return baseMapper.findByRoleId(roleId);
    }
}
