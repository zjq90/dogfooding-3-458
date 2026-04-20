package com.zoushiyou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zoushiyou.entity.SysLogs;
import com.zoushiyou.mapper.SysLogsMapper;
import com.zoushiyou.service.SysLogsService;
import org.springframework.stereotype.Service;

@Service
public class SysLogsServiceImpl extends ServiceImpl<SysLogsMapper, SysLogs> implements SysLogsService {
}
