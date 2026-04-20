package com.zoushiyou.mapper;

import com.zoushiyou.entity.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysLogMapper {

    @Select("SELECT * FROM tb_syslogs WHERE id = #{id}")
    SysLog selectById(Long id);

    @Select("<script>" +
            "SELECT * FROM tb_syslogs WHERE 1=1 " +
            "<if test='keyword != null and keyword != \"\"'>" +
            "AND operer_content LIKE CONCAT('%', #{keyword}, '%')" +
            "</if>" +
            "ORDER BY create_time DESC" +
            "</script>")
    List<SysLog> selectList(String keyword);

    @Insert("INSERT INTO tb_syslogs (id, name, operer_type, operer_content, create_id, create_time) " +
            "VALUES (#{id}, #{name}, #{opererType}, #{opererContent}, #{createId}, NOW())")
    int insert(SysLog sysLog);
}
