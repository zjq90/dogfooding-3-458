package com.zoushiyou.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zoushiyou.mapper.DeptInfoMapper;
import com.zoushiyou.model.entity.DeptInfo;
import org.springframework.stereotype.Service;

/**
 * 部门信息服务类
 */
@Service
public class DeptInfoService extends ServiceImpl<DeptInfoMapper, DeptInfo> {
}
