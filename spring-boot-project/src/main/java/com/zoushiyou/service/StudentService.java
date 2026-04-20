package com.zoushiyou.service;

import com.github.pagehelper.PageInfo;
import com.zoushiyou.dto.PageQueryDTO;
import com.zoushiyou.entity.Student;

import java.util.List;

public interface StudentService {
    Student getById(Long id);

    List<Student> getAll();

    PageInfo<Student> getPage(PageQueryDTO queryDTO);

    boolean save(Student student);

    boolean update(Student student);

    boolean delete(Long id, Long operatorId);
}
