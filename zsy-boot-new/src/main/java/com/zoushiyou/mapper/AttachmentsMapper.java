package com.zoushiyou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zoushiyou.model.entity.Attachments;
import org.apache.ibatis.annotations.Mapper;

/**
 * 附件信息Mapper
 */
@Mapper
public interface AttachmentsMapper extends BaseMapper<Attachments> {
}
