package com.zoushiyou.controller;

import com.github.pagehelper.PageInfo;
import com.zoushiyou.common.Result;
import com.zoushiyou.dto.PageQueryDTO;
import com.zoushiyou.entity.Student;
import com.zoushiyou.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "学生管理接口")
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @ApiOperation("根据ID获取学生")
    @GetMapping("/{id}")
    public Result<Student> getById(@PathVariable Long id) {
        Student student = studentService.getById(id);
        return Result.success(student);
    }

    @ApiOperation("获取所有学生")
    @GetMapping("/all")
    public Result<List<Student>> getAll() {
        List<Student> list = studentService.getAll();
        return Result.success(list);
    }

    @ApiOperation("分页查询学生列表")
    @GetMapping
    public Result<PageInfo<Student>> getPage(PageQueryDTO queryDTO) {
        PageInfo<Student> page = studentService.getPage(queryDTO);
        return Result.success(page);
    }

    @ApiOperation("新增学生")
    @PostMapping
    public Result<Void> save(@RequestBody Student student) {
        studentService.save(student);
        return Result.success();
    }

    @ApiOperation("更新学生")
    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @RequestBody Student student) {
        student.setId(id);
        studentService.update(student);
        return Result.success();
    }

    @ApiOperation("删除学生")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        studentService.delete(id, 1L);
        return Result.success();
    }
}
