package com.zoushiyou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zoushiyou.entity.DeptInfo;
import com.zoushiyou.mapper.DeptInfoMapper;
import com.zoushiyou.service.DeptInfoService;
import org.springframework.stereotype.Service;

@Service
public class DeptInfoServiceImpl extends ServiceImpl<DeptInfoMapper, DeptInfo> implements DeptInfoService {
}
