package com.zoushiyou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zoushiyou.model.entity.QuartzJob;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务Mapper
 */
@Mapper
public interface QuartzJobMapper extends BaseMapper<QuartzJob> {
}
