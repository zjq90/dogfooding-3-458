package com.zoushiyou.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zoushiyou.mapper.SysLogsMapper;
import com.zoushiyou.model.entity.SysLogs;
import org.springframework.stereotype.Service;

/**
 * 系统日志服务类
 */
@Service
public class SysLogsService extends ServiceImpl<SysLogsMapper, SysLogs> {
}
