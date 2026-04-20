package com.zoushiyou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zoushiyou.common.BusinessException;
import com.zoushiyou.dto.PageQueryDTO;
import com.zoushiyou.entity.Student;
import com.zoushiyou.mapper.StudentMapper;
import com.zoushiyou.service.StudentService;
import com.zoushiyou.util.SnowflakeIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentMapper studentMapper;
    private final SnowflakeIdGenerator idGenerator;

    @Override
    public Student getById(Long id) {
        return studentMapper.selectById(id);
    }

    @Override
    public List<Student> getAll() {
        return studentMapper.selectList(null);
    }

    @Override
    public PageInfo<Student> getPage(PageQueryDTO queryDTO) {
        PageHelper.startPage(queryDTO.getPageNum(), queryDTO.getPageSize());
        List<Student> list = studentMapper.selectList(queryDTO.getKeyword());
        return new PageInfo<>(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(Student student) {
        student.setId(idGenerator.nextId());
        student.setCreateTime(LocalDateTime.now());
        student.setVersion(1);
        student.setIsDelete(0);
        student.setIsEnable(1);
        if (student.getSortNum() == null) {
            student.setSortNum(1);
        }
        return studentMapper.insert(student) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        int result = studentMapper.update(student);
        if (result == 0) {
            throw new BusinessException("数据已被修改，请刷新后重试");
        }
        return result > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id, Long operatorId) {
        return studentMapper.deleteById(id, operatorId) > 0;
    }
}
