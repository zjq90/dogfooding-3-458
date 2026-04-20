package com.zoushiyou.config;

import com.zoushiyou.entity.*;
import com.zoushiyou.service.*;
import com.zoushiyou.util.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DatabaseInitializer implements CommandLineRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);
    
    @Autowired
    private UserInfoService userInfoService;
    
    @Autowired
    private RoleInfoService roleInfoService;
    
    @Autowired
    private DeptInfoService deptInfoService;
    
    @Override
    public void run(String... args) throws Exception {
        logger.info("开始初始化数据库...");
        try {
            initDefaultData();
            logger.info("数据库初始化完成！");
        } catch (Exception e) {
            logger.warn("数据库初始化跳过（表可能尚未创建）: {}", e.getMessage());
        }
    }
    
    private void initDefaultData() {
        try {
            if (roleInfoService.count() == 0) {
                RoleInfo role1 = new RoleInfo();
                role1.setId(1L);
                role1.setCode("1");
                role1.setName("超级管理员");
                role1.setSortNum(1);
                role1.setCreateTime(new Date());
                roleInfoService.save(role1);
                
                RoleInfo role2 = new RoleInfo();
                role2.setId(2L);
                role2.setCode("2");
                role2.setName("管理员");
                role2.setSortNum(2);
                role2.setCreateTime(new Date());
                roleInfoService.save(role2);
            }
        } catch (Exception e) {
            logger.debug("角色表初始化跳过: {}", e.getMessage());
        }
        
        try {
            if (deptInfoService.count() == 0) {
                DeptInfo dept1 = new DeptInfo();
                dept1.setId(1L);
                dept1.setCode("1");
                dept1.setName("开发部");
                dept1.setSortNum(1);
                dept1.setCreateTime(new Date());
                deptInfoService.save(dept1);
            }
        } catch (Exception e) {
            logger.debug("部门表初始化跳过: {}", e.getMessage());
        }
        
        try {
            if (userInfoService.count() == 0) {
                UserInfo admin = new UserInfo();
                admin.setId(1L);
                admin.setCode("admin");
                admin.setName("超级用户");
                String salt = Helper.getUUID();
                admin.setSalt(salt);
                String defaultPwd = "123456";
                admin.setPassWord(Helper.getMd5Str(defaultPwd, "admin" + salt));
                admin.setIsMale(1);
                admin.setPhoneNum("15812345678");
                admin.setRoleId(1L);
                admin.setDeptId(1L);
                admin.setSortNum(1);
                admin.setCreateTime(new Date());
                userInfoService.save(admin);
            }
        } catch (Exception e) {
            logger.debug("用户表初始化跳过: {}", e.getMessage());
        }
    }
}
