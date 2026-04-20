package com.zoushiyou.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.time.LocalDateTime;

/**
 * 数据库初始化器
 */
@Slf4j
@Component
public class DataInitializer implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    public DataInitializer(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void run(String... args) throws Exception {
        // 检查表是否存在
        if (!isTableExists("tb_userinfo")) {
            log.info("数据库表不存在，开始初始化...");
            initDatabase();
            log.info("数据库初始化完成！");
        } else {
            log.info("数据库表已存在，跳过初始化。");
        }
    }

    /**
     * 检查表是否存在
     */
    private boolean isTableExists(String tableName) {
        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet tables = metaData.getTables(null, null, tableName.toUpperCase(), null);
            return tables.next();
        } catch (Exception e) {
            log.error("检查表是否存在失败", e);
            return false;
        }
    }

    /**
     * 初始化数据库
     */
    private void initDatabase() {
        try {
            // 创建用户表
            jdbcTemplate.execute(
                "CREATE TABLE IF NOT EXISTS tb_userinfo (" +
                "    id BIGINT PRIMARY KEY," +
                "    parent_id BIGINT DEFAULT 0," +
                "    version INT DEFAULT 1," +
                "    code VARCHAR(50)," +
                "    name VARCHAR(50)," +
                "    remarks VARCHAR(150)," +
                "    sort_num INT DEFAULT 1," +
                "    is_enable INT DEFAULT 1," +
                "    is_delete INT DEFAULT 0," +
                "    owner_id BIGINT," +
                "    create_id BIGINT," +
                "    update_id BIGINT," +
                "    create_time TIMESTAMP," +
                "    update_time TIMESTAMP," +
                "    pass_word VARCHAR(100)," +
                "    salt VARCHAR(50)," +
                "    is_male INT DEFAULT 1," +
                "    phone_num VARCHAR(20)," +
                "    role_id BIGINT," +
                "    dept_id BIGINT" +
                ")"
            );

            // 创建部门表
            jdbcTemplate.execute(
                "CREATE TABLE IF NOT EXISTS tb_deptinfo (" +
                "    id BIGINT PRIMARY KEY," +
                "    parent_id BIGINT DEFAULT 0," +
                "    version INT DEFAULT 1," +
                "    code VARCHAR(50)," +
                "    name VARCHAR(50)," +
                "    remarks VARCHAR(150)," +
                "    sort_num INT DEFAULT 1," +
                "    is_enable INT DEFAULT 1," +
                "    is_delete INT DEFAULT 0," +
                "    owner_id BIGINT," +
                "    create_id BIGINT," +
                "    update_id BIGINT," +
                "    create_time TIMESTAMP," +
                "    update_time TIMESTAMP" +
                ")"
            );

            // 创建角色表
            jdbcTemplate.execute(
                "CREATE TABLE IF NOT EXISTS tb_roleinfo (" +
                "    id BIGINT PRIMARY KEY," +
                "    parent_id BIGINT DEFAULT 0," +
                "    version INT DEFAULT 1," +
                "    code VARCHAR(50)," +
                "    name VARCHAR(50)," +
                "    remarks VARCHAR(150)," +
                "    sort_num INT DEFAULT 1," +
                "    is_enable INT DEFAULT 1," +
                "    is_delete INT DEFAULT 0," +
                "    owner_id BIGINT," +
                "    create_id BIGINT," +
                "    update_id BIGINT," +
                "    create_time TIMESTAMP," +
                "    update_time TIMESTAMP" +
                ")"
            );

            // 创建功能菜单表
            jdbcTemplate.execute(
                "CREATE TABLE IF NOT EXISTS tb_funcinfo (" +
                "    id BIGINT PRIMARY KEY," +
                "    parent_id BIGINT DEFAULT 0," +
                "    version INT DEFAULT 1," +
                "    code VARCHAR(50)," +
                "    name VARCHAR(50)," +
                "    remarks VARCHAR(150)," +
                "    sort_num INT DEFAULT 1," +
                "    is_enable INT DEFAULT 1," +
                "    is_delete INT DEFAULT 0," +
                "    owner_id BIGINT," +
                "    create_id BIGINT," +
                "    update_id BIGINT," +
                "    create_time TIMESTAMP," +
                "    update_time TIMESTAMP," +
                "    route_path VARCHAR(150)," +
                "    style_name VARCHAR(150)," +
                "    level_val INT" +
                ")"
            );

            // 创建角色功能关联表
            jdbcTemplate.execute(
                "CREATE TABLE IF NOT EXISTS tb_rolejoinfunc (" +
                "    id BIGINT PRIMARY KEY," +
                "    parent_id BIGINT DEFAULT 0," +
                "    version INT DEFAULT 1," +
                "    code VARCHAR(50)," +
                "    name VARCHAR(50)," +
                "    remarks VARCHAR(150)," +
                "    sort_num INT DEFAULT 1," +
                "    is_enable INT DEFAULT 1," +
                "    is_delete INT DEFAULT 0," +
                "    owner_id BIGINT," +
                "    create_id BIGINT," +
                "    update_id BIGINT," +
                "    create_time TIMESTAMP," +
                "    update_time TIMESTAMP," +
                "    role_id BIGINT," +
                "    func_id BIGINT," +
                "    level_val INT" +
                ")"
            );

            // 创建学生表
            jdbcTemplate.execute(
                "CREATE TABLE IF NOT EXISTS tb_student (" +
                "    id BIGINT PRIMARY KEY," +
                "    parent_id BIGINT DEFAULT 0," +
                "    version INT DEFAULT 1," +
                "    code VARCHAR(50)," +
                "    name VARCHAR(50)," +
                "    remarks VARCHAR(150)," +
                "    sort_num INT DEFAULT 1," +
                "    is_enable INT DEFAULT 1," +
                "    is_delete INT DEFAULT 0," +
                "    owner_id BIGINT," +
                "    create_id BIGINT," +
                "    update_id BIGINT," +
                "    create_time TIMESTAMP," +
                "    update_time TIMESTAMP" +
                ")"
            );

            // 创建系统日志表
            jdbcTemplate.execute(
                "CREATE TABLE IF NOT EXISTS tb_syslogs (" +
                "    id BIGINT PRIMARY KEY," +
                "    parent_id BIGINT DEFAULT 0," +
                "    version INT DEFAULT 1," +
                "    code VARCHAR(50)," +
                "    name VARCHAR(50)," +
                "    remarks VARCHAR(150)," +
                "    sort_num INT DEFAULT 1," +
                "    is_enable INT DEFAULT 1," +
                "    is_delete INT DEFAULT 0," +
                "    owner_id BIGINT," +
                "    create_id BIGINT," +
                "    update_id BIGINT," +
                "    create_time TIMESTAMP," +
                "    update_time TIMESTAMP," +
                "    operer_type INT," +
                "    operer_content VARCHAR(450)" +
                ")"
            );

            // 创建定时任务表
            jdbcTemplate.execute(
                "CREATE TABLE IF NOT EXISTS tb_quartzjob (" +
                "    id BIGINT PRIMARY KEY," +
                "    parent_id BIGINT DEFAULT 0," +
                "    version INT DEFAULT 1," +
                "    code VARCHAR(50)," +
                "    name VARCHAR(50)," +
                "    remarks VARCHAR(150)," +
                "    sort_num INT DEFAULT 1," +
                "    is_enable INT DEFAULT 1," +
                "    is_delete INT DEFAULT 0," +
                "    owner_id BIGINT," +
                "    create_id BIGINT," +
                "    update_id BIGINT," +
                "    create_time TIMESTAMP," +
                "    update_time TIMESTAMP," +
                "    job_group VARCHAR(50)," +
                "    job_status INT," +
                "    job_cron VARCHAR(50)" +
                ")"
            );

            // 创建附件表
            jdbcTemplate.execute(
                "CREATE TABLE IF NOT EXISTS tb_attachments (" +
                "    id BIGINT PRIMARY KEY," +
                "    parent_id BIGINT DEFAULT 0," +
                "    version INT DEFAULT 1," +
                "    code VARCHAR(50)," +
                "    name VARCHAR(50)," +
                "    remarks VARCHAR(150)," +
                "    sort_num INT DEFAULT 1," +
                "    is_enable INT DEFAULT 1," +
                "    is_delete INT DEFAULT 0," +
                "    owner_id BIGINT," +
                "    create_id BIGINT," +
                "    update_id BIGINT," +
                "    create_time TIMESTAMP," +
                "    update_time TIMESTAMP," +
                "    file_path VARCHAR(255)," +
                "    file_size BIGINT," +
                "    file_type VARCHAR(50)" +
                ")"
            );

            // 插入初始化数据
            initData();

        } catch (Exception e) {
            log.error("初始化数据库失败", e);
        }
    }

    /**
     * 插入初始化数据
     */
    private void initData() {
        LocalDateTime now = LocalDateTime.now();

        // 插入默认部门
        jdbcTemplate.update(
            "INSERT INTO tb_deptinfo (id, parent_id, version, code, name, remarks, sort_num, is_enable, is_delete, owner_id, create_id, create_time) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            1L, 0L, 1, "DEPT001", "技术部", "技术研发部门", 1, 1, 0, 1L, 1L, now
        );

        // 插入默认角色
        jdbcTemplate.update(
            "INSERT INTO tb_roleinfo (id, parent_id, version, code, name, remarks, sort_num, is_enable, is_delete, owner_id, create_id, create_time) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            1L, 0L, 1, "ADMIN", "管理员", "系统管理员", 1, 1, 0, 1L, 1L, now
        );

        // 插入默认菜单
        jdbcTemplate.update(
            "INSERT INTO tb_funcinfo (id, parent_id, version, code, name, remarks, sort_num, is_enable, is_delete, owner_id, create_id, create_time, route_path, style_name, level_val) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            1L, 0L, 1, "SYSTEM", "系统管理", "系统管理菜单", 1, 1, 0, 1L, 1L, now, "/system", "el-icon-setting", 1
        );

        // 插入角色功能关联
        jdbcTemplate.update(
            "INSERT INTO tb_rolejoinfunc (id, parent_id, version, code, name, remarks, sort_num, is_enable, is_delete, owner_id, create_id, create_time, role_id, func_id, level_val) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            1L, 0L, 1, "", "", "", 1, 1, 0, 1L, 1L, now, 1L, 1L, 1
        );

        // 插入默认用户（密码：123456）
        String salt = "abc123";
        String password = org.springframework.util.DigestUtils.md5DigestAsHex(
            ("123456" + "admin" + salt).getBytes(java.nio.charset.StandardCharsets.UTF_8)
        );
        jdbcTemplate.update(
            "INSERT INTO tb_userinfo (id, parent_id, version, code, name, remarks, sort_num, is_enable, is_delete, owner_id, create_id, create_time, pass_word, salt, is_male, phone_num, role_id, dept_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            1L, 0L, 1, "admin", "管理员", "系统默认管理员", 1, 1, 0, 1L, 1L, now,
            password, salt, 1, "13800138000", 1L, 1L
        );

        log.info("初始化数据插入完成");
    }
}
