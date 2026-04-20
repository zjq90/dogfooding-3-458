package com.zoushiyou.mapper;

import com.zoushiyou.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {

    @Select("SELECT * FROM tb_student WHERE id = #{id} AND is_delete = 0")
    Student selectById(Long id);

    @Select("<script>" +
            "SELECT * FROM tb_student WHERE is_delete = 0 " +
            "<if test='keyword != null and keyword != \"\"'>" +
            "AND (name LIKE CONCAT('%', #{keyword}, '%') OR code LIKE CONCAT('%', #{keyword}, '%'))" +
            "</if>" +
            "ORDER BY sort_num ASC, create_time DESC" +
            "</script>")
    List<Student> selectList(String keyword);

    @Insert("INSERT INTO tb_student (id, parent_id, version, code, name, remarks, sort_num, " +
            "is_enable, is_delete, owner_id, create_id, update_id, create_time, update_time) " +
            "VALUES (#{id}, #{parentId}, #{version}, #{code}, #{name}, #{remarks}, #{sortNum}, " +
            "#{isEnable}, #{isDelete}, #{ownerId}, #{createId}, #{updateId}, #{createTime}, #{updateTime})")
    int insert(Student student);

    @Update("UPDATE tb_student SET parent_id = #{parentId}, version = version + 1, code = #{code}, " +
            "name = #{name}, remarks = #{remarks}, sort_num = #{sortNum}, is_enable = #{isEnable}, " +
            "owner_id = #{ownerId}, update_id = #{updateId}, update_time = #{updateTime} " +
            "WHERE id = #{id} AND version = #{version}")
    int update(Student student);

    @Update("UPDATE tb_student SET is_delete = 1, update_id = #{updateId}, update_time = NOW() WHERE id = #{id}")
    int deleteById(@Param("id") Long id, @Param("updateId") Long updateId);
}
