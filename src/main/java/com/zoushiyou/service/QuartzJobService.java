package com.zoushiyou.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zoushiyou.mapper.QuartzJobMapper;
import com.zoushiyou.model.entity.QuartzJob;
import org.springframework.stereotype.Service;

/**
 * 定时任务服务类
 */
@Service
public class QuartzJobService extends ServiceImpl<QuartzJobMapper, QuartzJob> {
}
