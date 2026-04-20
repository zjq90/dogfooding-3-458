-- H2 Database Schema
-- 用户表
CREATE TABLE IF NOT EXISTS tb_userinfo (
    id BIGINT PRIMARY KEY,
    parent_id BIGINT DEFAULT 0,
    version INT DEFAULT 1,
    code VARCHAR(50),
    name VARCHAR(50),
    remarks VARCHAR(150),
    sort_num INT DEFAULT 1,
    is_enable INT DEFAULT 1,
    is_delete INT DEFAULT 0,
    owner_id BIGINT,
    create_id BIGINT,
    update_id BIGINT,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    pass_word VARCHAR(100),
    salt VARCHAR(50),
    is_male INT DEFAULT 1,
    phone_num VARCHAR(20),
    role_id BIGINT,
    dept_id BIGINT
);

-- 部门表
CREATE TABLE IF NOT EXISTS tb_deptinfo (
    id BIGINT PRIMARY KEY,
    parent_id BIGINT DEFAULT 0,
    version INT DEFAULT 1,
    code VARCHAR(50),
    name VARCHAR(50),
    remarks VARCHAR(150),
    sort_num INT DEFAULT 1,
    is_enable INT DEFAULT 1,
    is_delete INT DEFAULT 0,
    owner_id BIGINT,
    create_id BIGINT,
    update_id BIGINT,
    create_time TIMESTAMP,
    update_time TIMESTAMP
);

-- 角色表
CREATE TABLE IF NOT EXISTS tb_roleinfo (
    id BIGINT PRIMARY KEY,
    parent_id BIGINT DEFAULT 0,
    version INT DEFAULT 1,
    code VARCHAR(50),
    name VARCHAR(50),
    remarks VARCHAR(150),
    sort_num INT DEFAULT 1,
    is_enable INT DEFAULT 1,
    is_delete INT DEFAULT 0,
    owner_id BIGINT,
    create_id BIGINT,
    update_id BIGINT,
    create_time TIMESTAMP,
    update_time TIMESTAMP
);

-- 功能菜单表
CREATE TABLE IF NOT EXISTS tb_funcinfo (
    id BIGINT PRIMARY KEY,
    parent_id BIGINT DEFAULT 0,
    version INT DEFAULT 1,
    code VARCHAR(50),
    name VARCHAR(50),
    remarks VARCHAR(150),
    sort_num INT DEFAULT 1,
    is_enable INT DEFAULT 1,
    is_delete INT DEFAULT 0,
    owner_id BIGINT,
    create_id BIGINT,
    update_id BIGINT,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    route_path VARCHAR(150),
    style_name VARCHAR(150),
    level_val INT
);

-- 角色功能关联表
CREATE TABLE IF NOT EXISTS tb_rolejoinfunc (
    id BIGINT PRIMARY KEY,
    parent_id BIGINT DEFAULT 0,
    version INT DEFAULT 1,
    code VARCHAR(50),
    name VARCHAR(50),
    remarks VARCHAR(150),
    sort_num INT DEFAULT 1,
    is_enable INT DEFAULT 1,
    is_delete INT DEFAULT 0,
    owner_id BIGINT,
    create_id BIGINT,
    update_id BIGINT,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    role_id BIGINT,
    func_id BIGINT,
    level_val INT
);

-- 学生表
CREATE TABLE IF NOT EXISTS tb_student (
    id BIGINT PRIMARY KEY,
    parent_id BIGINT DEFAULT 0,
    version INT DEFAULT 1,
    code VARCHAR(50),
    name VARCHAR(50),
    remarks VARCHAR(150),
    sort_num INT DEFAULT 1,
    is_enable INT DEFAULT 1,
    is_delete INT DEFAULT 0,
    owner_id BIGINT,
    create_id BIGINT,
    update_id BIGINT,
    create_time TIMESTAMP,
    update_time TIMESTAMP
);

-- 系统日志表
CREATE TABLE IF NOT EXISTS tb_syslogs (
    id BIGINT PRIMARY KEY,
    parent_id BIGINT DEFAULT 0,
    version INT DEFAULT 1,
    code VARCHAR(50),
    name VARCHAR(50),
    remarks VARCHAR(150),
    sort_num INT DEFAULT 1,
    is_enable INT DEFAULT 1,
    is_delete INT DEFAULT 0,
    owner_id BIGINT,
    create_id BIGINT,
    update_id BIGINT,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    operer_type INT,
    operer_content VARCHAR(450)
);

-- 定时任务表
CREATE TABLE IF NOT EXISTS tb_quartzjob (
    id BIGINT PRIMARY KEY,
    parent_id BIGINT DEFAULT 0,
    version INT DEFAULT 1,
    code VARCHAR(50),
    name VARCHAR(50),
    remarks VARCHAR(150),
    sort_num INT DEFAULT 1,
    is_enable INT DEFAULT 1,
    is_delete INT DEFAULT 0,
    owner_id BIGINT,
    create_id BIGINT,
    update_id BIGINT,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    job_group VARCHAR(50),
    job_status INT,
    job_cron VARCHAR(50)
);

-- 附件表
CREATE TABLE IF NOT EXISTS tb_attachments (
    id BIGINT PRIMARY KEY,
    parent_id BIGINT DEFAULT 0,
    version INT DEFAULT 1,
    code VARCHAR(50),
    name VARCHAR(50),
    remarks VARCHAR(150),
    sort_num INT DEFAULT 1,
    is_enable INT DEFAULT 1,
    is_delete INT DEFAULT 0,
    owner_id BIGINT,
    create_id BIGINT,
    update_id BIGINT,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    file_path VARCHAR(255),
    file_size BIGINT,
    file_type VARCHAR(50)
);
