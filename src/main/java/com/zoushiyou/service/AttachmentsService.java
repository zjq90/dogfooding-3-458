package com.zoushiyou.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zoushiyou.mapper.AttachmentsMapper;
import com.zoushiyou.model.entity.Attachments;
import org.springframework.stereotype.Service;

/**
 * 附件信息服务类
 */
@Service
public class AttachmentsService extends ServiceImpl<AttachmentsMapper, Attachments> {
}
