package com.zoushiyou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zoushiyou.model.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户信息Mapper
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    /**
     * 根据用户名查询用户
     */
    @Select("SELECT * FROM tb_userinfo WHERE code = #{username} AND is_delete = 0")
    UserInfo findByUserName(@Param("username") String username);
}
