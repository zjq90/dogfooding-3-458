package com.zoushiyou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zoushiyou.entity.Attachments;
import com.zoushiyou.mapper.AttachmentsMapper;
import com.zoushiyou.service.AttachmentsService;
import org.springframework.stereotype.Service;

@Service
public class AttachmentsServiceImpl extends ServiceImpl<AttachmentsMapper, Attachments> implements AttachmentsService {
}
