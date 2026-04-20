package com.zoushiyou.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zoushiyou.mapper.StudentMapper;
import com.zoushiyou.model.entity.Student;
import org.springframework.stereotype.Service;

/**
 * 学生信息服务类
 */
@Service
public class StudentService extends ServiceImpl<StudentMapper, Student> {
}
