package com.zoushiyou.mapper;

import com.zoushiyou.entity.Func;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FuncMapper {

    @Select("SELECT * FROM tb_funcinfo WHERE id = #{id} AND is_delete = 0")
    Func selectById(Long id);

    @Select("SELECT f.* FROM tb_funcinfo f " +
            "INNER JOIN tb_rolejoinfunc rf ON f.id = rf.func_id " +
            "WHERE rf.role_id = #{roleId} AND f.is_delete = 0 AND rf.is_delete = 0 " +
            "ORDER BY f.sort_num ASC")
    List<Func> selectByRoleId(Long roleId);

    @Select("<script>" +
            "SELECT * FROM tb_funcinfo WHERE is_delete = 0 " +
            "<if test='keyword != null and keyword != \"\"'>" +
            "AND (name LIKE CONCAT('%', #{keyword}, '%') OR code LIKE CONCAT('%', #{keyword}, '%'))" +
            "</if>" +
            "ORDER BY sort_num ASC, create_time DESC" +
            "</script>")
    List<Func> selectList(String keyword);

    @Insert("INSERT INTO tb_funcinfo (id, parent_id, version, code, name, remarks, sort_num, " +
            "is_enable, is_delete, owner_id, create_id, update_id, create_time, update_time, " +
            "route_path, style_name, level_val) " +
            "VALUES (#{id}, #{parentId}, #{version}, #{code}, #{name}, #{remarks}, #{sortNum}, " +
            "#{isEnable}, #{isDelete}, #{ownerId}, #{createId}, #{updateId}, #{createTime}, #{updateTime}, " +
            "#{routePath}, #{styleName}, #{levelVal})")
    int insert(Func func);

    @Update("UPDATE tb_funcinfo SET parent_id = #{parentId}, version = version + 1, code = #{code}, " +
            "name = #{name}, remarks = #{remarks}, sort_num = #{sortNum}, is_enable = #{isEnable}, " +
            "owner_id = #{ownerId}, update_id = #{updateId}, update_time = #{updateTime}, " +
            "route_path = #{routePath}, style_name = #{styleName}, level_val = #{levelVal} " +
            "WHERE id = #{id} AND version = #{version}")
    int update(Func func);

    @Update("UPDATE tb_funcinfo SET is_delete = 1, update_id = #{updateId}, update_time = NOW() WHERE id = #{id}")
    int deleteById(@Param("id") Long id, @Param("updateId") Long updateId);
}
