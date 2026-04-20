package com.zoushiyou.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zoushiyou.common.ResultVo;
import com.zoushiyou.entity.Student;
import com.zoushiyou.exception.BusinessException;
import com.zoushiyou.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(tags = "学生管理")
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    
    @Autowired
    private StudentService studentService;
    
    @ApiOperation("创建学生")
    @PostMapping
    public ResultVo<Student> create(@RequestBody Student student) {
        student.setCreateTime(new Date());
        studentService.save(student);
        return ResultVo.success(student);
    }
    
    @ApiOperation("获取学生详情")
    @GetMapping("/{id}")
    public ResultVo<Student> getById(@PathVariable Long id) {
        Student student = studentService.getById(id);
        if (student == null) {
            throw new BusinessException("学生不存在");
        }
        return ResultVo.success(student);
    }
    
    @ApiOperation("分页查询学生列表")
    @GetMapping
    public ResultVo<Page<Student>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String name) {
        Page<Student> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            wrapper.like(Student::getName, name);
        }
        wrapper.orderByAsc(Student::getSortNum);
        studentService.page(page, wrapper);
        return ResultVo.success(page, (int) page.getTotal());
    }
    
    @ApiOperation("更新学生")
    @PutMapping("/{id}")
    public ResultVo<Student> update(@PathVariable Long id, @RequestBody Student student) {
        Student existStudent = studentService.getById(id);
        if (existStudent == null) {
            throw new BusinessException("学生不存在");
        }
        student.setId(id);
        student.setUpdateTime(new Date());
        studentService.updateById(student);
        return ResultVo.success(student);
    }
    
    @ApiOperation("删除学生")
    @DeleteMapping("/{id}")
    public ResultVo<Boolean> delete(@PathVariable Long id) {
        boolean result = studentService.removeById(id);
        if (!result) {
            throw new BusinessException("删除学生失败");
        }
        return ResultVo.success(true);
    }
}
