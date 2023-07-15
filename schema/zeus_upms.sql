USE zeus_upms;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_tenant_plan
-- ----------------------------
DROP TABLE IF EXISTS `sys_tenant_plan`;
CREATE TABLE `sys_tenant_plan`
(
    `id`          bigint        not null auto_increment comment '租户套餐id',
    `name`        varchar(64)   not null comment '租户套餐名称',
    `status`      tinyint       NOT NULL COMMENT '状态',
    `menu_ids`    varchar(2048) NOT NULL COMMENT '关联的菜单编号',
    `create_by`   varchar(64)   not null comment '创建人',
    `create_time` datetime      not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '创建时间',
    `update_by`   varchar(64)   NOT NULL DEFAULT ' ' COMMENT '修改人',
    `update_time` datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)        NOT NULL DEFAULT b'0' COMMENT '逻辑删除（0：正常 1：删除）',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '租户套餐';

-- ----------------------------
-- Table structure for sys_tenant
-- ----------------------------
DROP TABLE IF EXISTS `sys_tenant`;
CREATE TABLE `sys_tenant`
(
    `id`               bigint      not null auto_increment comment '租户id',
    `name`             varchar(64) not null comment '租户名称',
    `tenant_plan_id`   int         not null comment '租户套餐id',
    `tenant_plan_name` varchar(64) not null comment '租户套餐名称',
    `status`           tinyint     NOT NULL COMMENT '状态（0：正常 1：停用）',
    `create_by`        varchar(64) not null comment '创建人',
    `create_time`      datetime    not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '创建时间',
    `update_by`        varchar(64) NOT NULL DEFAULT ' ' COMMENT '修改人',
    `update_time`      datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`          bit(1)      NOT NULL DEFAULT b'0' COMMENT '逻辑删除（0：正常 1：删除）',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '租户';

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`
(
    `id`          bigint      NOT NULL auto_increment COMMENT '部门id',
    `name`        varchar(64) not NULL COMMENT '部门名称',
    `parent_id`   bigint      not null DEFAULT 0 COMMENT '父级部门id',
    `level`       int         NOT NUlL COMMENT '部门等级；最高级部门为 1，依次递增',
    `sort`        int         NOT NULL DEFAULT '0' COMMENT '排序',
    `status`      tinyint     NOT NULL COMMENT '部门状态（0正常 1停用）',
    `create_by`   varchar(64) not null comment '创建人',
    `create_time` datetime    not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '创建时间',
    `update_by`   varchar(64) NOT NULL DEFAULT ' ' COMMENT '修改人',
    `update_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)      NOT NULL DEFAULT b'0' COMMENT '逻辑删除（0：正常 1：删除）',
    `tenant_id`   bigint               DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='部门管理';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `id`          bigint       not null auto_increment comment '菜单id',
    `name`        varchar(64)  not null comment '菜单名称',
    `parent_id`   bigint       NOT NULL DEFAULT 0 COMMENT '父菜单ID',
    `type`        tinyint      NOT NULL COMMENT '菜单类型',
    `sort`        int          NOT NULL DEFAULT 0 COMMENT '显示顺序',
    `permission`  varchar(100) NOT NULL DEFAULT '' COMMENT '权限标识',
    `path`        varchar(200) NULL     DEFAULT '' COMMENT '路由地址',
    `icon`        varchar(100) NULL     DEFAULT '#' COMMENT '菜单图标',
    `component`   varchar(255) NULL     DEFAULT NULL COMMENT '组件路径',
    `status`      tinyint      NOT NULL COMMENT '状态（0：正常 1：停用）',
    `visible`     bit(1)       NOT NULL DEFAULT b'1' COMMENT '是否可见',
    `create_by`   varchar(64)  not null comment '创建人',
    `create_time` datetime     not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '创建时间',
    `update_by`   varchar(64)  NOT NULL DEFAULT ' ' COMMENT '修改人',
    `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)       NOT NULL DEFAULT b'0' COMMENT '逻辑删除（0：正常 1：删除）',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`                  bigint        not null auto_increment comment '角色 id',
    `name`                varchar(64)   not null comment '角色名称',
    `code`                varchar(64)   not null comment '角色权限编码',
    `type`                tinyint       NOT NULL COMMENT '角色类型',
    `sort`                int           NOT NULL DEFAULT 0 COMMENT '显示顺序',
    `data_scope`          tinyint       NOT NULL DEFAULT 1 COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
    `data_scope_dept_ids` varchar(1024) NOT NULL DEFAULT '' COMMENT '数据范围(指定部门数组)',
    `status`              tinyint       NOT NULL COMMENT '状态（0：正常 1：停用）',
    `remark`              varchar(500)  NULL     DEFAULT NULL COMMENT '备注',
    `create_by`           varchar(64)   not null comment '创建人',
    `create_time`         datetime      not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '创建时间',
    `update_by`           varchar(64)   NOT NULL DEFAULT ' ' COMMENT '修改人',
    `update_time`         datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`             bit(1)        NOT NULL DEFAULT b'0' COMMENT '逻辑删除（0：正常 1：删除）',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色';

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `id`          bigint      NOT NULL AUTO_INCREMENT COMMENT '自增编号',
    `role_id`     bigint      NOT NULL COMMENT '角色ID',
    `menu_id`     bigint      NOT NULL COMMENT '菜单ID',
    `create_by`   varchar(64) not null comment '创建人',
    `create_time` datetime    not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '创建时间',
    `update_by`   varchar(64) NOT NULL DEFAULT ' ' COMMENT '修改人',
    `update_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)      NOT NULL DEFAULT b'0' COMMENT '逻辑删除（0：正常 1：删除）',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色菜单关联表';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`          bigint      NOT NULL COMMENT '用户ID',
    `username`    varchar(64) NOT NULL COMMENT '用户名称',
    `password`    varchar(255)         DEFAULT NULL COMMENT '密码',
    `nickname`    varchar(64)          DEFAULT NULL COMMENT '拓展字段:昵称',
    `salt`        varchar(255)         DEFAULT NULL COMMENT '盐值',
    `sex`         tinyint     NULL     DEFAULT 0 COMMENT '用户性别',
    `phone`       varchar(20)          DEFAULT NULL COMMENT '手机号码',
    `email`       varchar(128)         DEFAULT NULL COMMENT '拓展字段:邮箱',
    `avatar`      varchar(255)         DEFAULT NULL COMMENT '头像',
    `dept_id`     bigint               DEFAULT NULL COMMENT '部门ID',
    `lock_flag`   char(1)              DEFAULT '0' COMMENT '锁定标记：0(未锁定)、1(已锁定)',
    `status`      tinyint     NOT NULL COMMENT '状态（0：正常 1：停用）',
    `login_ip`    varchar(50) NULL     DEFAULT '' COMMENT '最后登录IP',
    `login_date`  datetime    NULL     DEFAULT NULL COMMENT '最后登录时间',
    `tenant_id`   bigint      NOT NULL DEFAULT '0' COMMENT '所属租户',
    `create_by`   varchar(64) not null comment '创建人',
    `create_time` datetime    not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '创建时间',
    `update_by`   varchar(64) NOT NULL DEFAULT ' ' COMMENT '修改人',
    `update_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)      NOT NULL DEFAULT b'0' COMMENT '逻辑删除（0：正常 1：删除）',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `user_idx1_username` (`username`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='用户表';