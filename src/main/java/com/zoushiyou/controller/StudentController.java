package com.zoushiyou.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zoushiyou.model.dto.PageDto;
import com.zoushiyou.model.dto.ResultVo;
import com.zoushiyou.model.entity.Student;
import com.zoushiyou.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 学生信息控制器
 */
@RestController
@RequestMapping("/api/v1/students")
@Tag(name = "学生管理", description = "学生信息相关接口")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 分页查询学生列表
     */
    @GetMapping
    @Operation(summary = "查询学生列表", description = "分页查询学生信息")
    public ResultVo<PageDto<Student>> list(
            @RequestParam(defaultValue = "1") Integer pageIndex,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String keyword) {

        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Student::getIsDelete, 0);

        if (StringUtils.hasText(keyword)) {
            wrapper.and(w -> w.like(Student::getCode, keyword)
                    .or()
                    .like(Student::getName, keyword));
        }

        wrapper.orderByAsc(Student::getSortNum);
        Page<Student> page = studentService.page(new Page<>(pageIndex, pageSize), wrapper);

        PageDto<Student> pageDto = new PageDto<>();
        pageDto.setPageIndex((int) page.getCurrent());
        pageDto.setPageSize((int) page.getSize());
        pageDto.setTotal(page.getTotal());
        pageDto.setRecords(page.getRecords());

        return ResultVo.success(pageDto, (int) page.getTotal());
    }

    /**
     * 根据ID查询学生
     */
    @GetMapping("/{id}")
    @Operation(summary = "查询学生详情", description = "根据ID查询学生详细信息")
    public ResultVo<Student> getById(@PathVariable Long id) {
        Student student = studentService.getById(id);
        if (student == null || (student.getIsDelete() != null && student.getIsDelete() == 1)) {
            return ResultVo.error("学生不存在");
        }
        return ResultVo.success(student);
    }

    /**
     * 创建学生
     */
    @PostMapping
    @Operation(summary = "创建学生", description = "创建新学生")
    public ResultVo<Student> create(@Valid @RequestBody Student student) {
        studentService.save(student);
        return ResultVo.success(student);
    }

    /**
     * 更新学生
     */
    @PutMapping("/{id}")
    @Operation(summary = "更新学生", description = "更新学生信息")
    public ResultVo<Student> update(@PathVariable Long id, @Valid @RequestBody Student student) {
        Student existStudent = studentService.getById(id);
        if (existStudent == null || (existStudent.getIsDelete() != null && existStudent.getIsDelete() == 1)) {
            return ResultVo.error("学生不存在");
        }

        student.setId(id);
        boolean success = studentService.updateById(student);
        if (!success) {
            return ResultVo.error("更新失败");
        }

        return ResultVo.success(studentService.getById(id));
    }

    /**
     * 删除学生（逻辑删除）
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除学生", description = "逻辑删除学生")
    public ResultVo<Boolean> delete(@PathVariable Long id) {
        boolean success = studentService.removeById(id);
        if (!success) {
            return ResultVo.error("删除失败");
        }
        return ResultVo.success(true);
    }
}
