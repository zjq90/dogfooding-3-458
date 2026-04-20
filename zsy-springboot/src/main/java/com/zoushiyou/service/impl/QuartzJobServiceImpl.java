package com.zoushiyou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zoushiyou.entity.QuartzJob;
import com.zoushiyou.mapper.QuartzJobMapper;
import com.zoushiyou.service.QuartzJobService;
import org.springframework.stereotype.Service;

@Service
public class QuartzJobServiceImpl extends ServiceImpl<QuartzJobMapper, QuartzJob> implements QuartzJobService {
}
