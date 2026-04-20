package com.zoushiyou.mapper;

import com.zoushiyou.entity.RoleFunc;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleFuncMapper {

    @Select("SELECT * FROM tb_rolejoinfunc WHERE id = #{id} AND is_delete = 0")
    RoleFunc selectById(Long id);

    @Select("SELECT * FROM tb_rolejoinfunc WHERE role_id = #{roleId} AND is_delete = 0")
    List<RoleFunc> selectByRoleId(Long roleId);

    @Delete("DELETE FROM tb_rolejoinfunc WHERE role_id = #{roleId}")
    int deleteByRoleId(Long roleId);

    @Insert("<script>" +
            "INSERT INTO tb_rolejoinfunc (id, role_id, func_id, level_val, create_id, create_time, is_delete) VALUES " +
            "<foreach collection='list' item='item' separator=','>" +
            "(#{item.id}, #{item.roleId}, #{item.funcId}, #{item.levelVal}, #{item.createId}, NOW(), 0)" +
            "</foreach>" +
            "</script>")
    int batchInsert(@Param("list") List<RoleFunc> list);
}
