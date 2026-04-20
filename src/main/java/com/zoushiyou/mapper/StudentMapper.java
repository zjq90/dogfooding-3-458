package com.zoushiyou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zoushiyou.model.entity.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * 学生信息Mapper
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
}
