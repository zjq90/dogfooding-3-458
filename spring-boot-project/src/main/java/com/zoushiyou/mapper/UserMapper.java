package com.zoushiyou.mapper;

import com.zoushiyou.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM tb_userinfo WHERE id = #{id} AND is_delete = 0")
    User selectById(Long id);

    @Select("SELECT * FROM tb_userinfo WHERE code = #{username} AND is_delete = 0")
    User selectByUsername(String username);

    @Select("<script>" +
            "SELECT u.*, r.name as roleName, d.name as deptName " +
            "FROM tb_userinfo u " +
            "LEFT JOIN tb_roleinfo r ON u.role_id = r.id " +
            "LEFT JOIN tb_deptinfo d ON u.dept_id = d.id " +
            "WHERE u.is_delete = 0 " +
            "<if test='keyword != null and keyword != \"\"'>" +
            "AND (u.name LIKE CONCAT('%', #{keyword}, '%') OR u.code LIKE CONCAT('%', #{keyword}, '%'))" +
            "</if>" +
            "ORDER BY u.sort_num ASC, u.create_time DESC" +
            "</script>")
    List<User> selectList(String keyword);

    @Insert("INSERT INTO tb_userinfo (id, parent_id, version, code, name, remarks, sort_num, " +
            "is_enable, is_delete, owner_id, create_id, update_id, create_time, update_time, " +
            "pass_word, salt, is_male, phone_num, role_id, dept_id) " +
            "VALUES (#{id}, #{parentId}, #{version}, #{code}, #{name}, #{remarks}, #{sortNum}, " +
            "#{isEnable}, #{isDelete}, #{ownerId}, #{createId}, #{updateId}, #{createTime}, #{updateTime}, " +
            "#{passWord}, #{salt}, #{isMale}, #{phoneNum}, #{roleId}, #{deptId})")
    int insert(User user);

    @Update("UPDATE tb_userinfo SET parent_id = #{parentId}, version = version + 1, code = #{code}, " +
            "name = #{name}, remarks = #{remarks}, sort_num = #{sortNum}, is_enable = #{isEnable}, " +
            "owner_id = #{ownerId}, update_id = #{updateId}, update_time = #{updateTime}, " +
            "pass_word = #{passWord}, is_male = #{isMale}, phone_num = #{phoneNum}, " +
            "role_id = #{roleId}, dept_id = #{deptId} " +
            "WHERE id = #{id} AND version = #{version}")
    int update(User user);

    @Update("UPDATE tb_userinfo SET is_delete = 1, update_id = #{updateId}, update_time = NOW() WHERE id = #{id}")
    int deleteById(@Param("id") Long id, @Param("updateId") Long updateId);
}
