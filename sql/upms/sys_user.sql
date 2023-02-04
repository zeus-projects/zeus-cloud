USE zeus;
-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`          bigint                         NOT NULL COMMENT '用户ID',
    `username`        varchar(64)                    NOT NULL COMMENT '用户名称',
    `password`    varchar(255)                            DEFAULT NULL COMMENT '密码',
    `nickname`    varchar(64)                             DEFAULT NULL COMMENT '拓展字段:昵称',
    `salt`        varchar(255)                            DEFAULT NULL COMMENT '盐值',
    `sex`         tinyint                        NULL     DEFAULT 0 COMMENT '用户性别',
    `phone`       varchar(20)                             DEFAULT NULL COMMENT '手机号码',
    `email`       varchar(128)                            DEFAULT NULL COMMENT '拓展字段:邮箱',
    `avatar`      varchar(255)                            DEFAULT NULL COMMENT '头像',
    `dept_id`     bigint                                  DEFAULT NULL COMMENT '部门ID',
    `lock_flag`   char(1)                                 DEFAULT '0' COMMENT '锁定标记：0(未锁定)、1(已锁定)',
    `status`      tinyint                        NOT NULL COMMENT '状态（0：正常 1：停用）',
    `login_ip`    varchar(50)                    NULL     DEFAULT '' COMMENT '最后登录IP',
    `login_date`  datetime                       NULL     DEFAULT NULL COMMENT '最后登录时间',
    `tenant_id`   bigint                         NOT NULL DEFAULT '0' COMMENT '所属租户',
    `create_by`   varchar(64)                    not null comment '创建人',
    `create_time` datetime                       not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '创建时间',
    `update_by`   varchar(64) NOT NULL DEFAULT ' ' COMMENT '修改人',
    `update_time` datetime                       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)                         NOT NULL DEFAULT b'0' COMMENT '逻辑删除（0：正常 1：删除）',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `user_idx1_username` (`username`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
