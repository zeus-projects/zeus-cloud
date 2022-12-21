USE zeus_boot;
-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `user_id`     bigint                            NOT NULL COMMENT '用户ID',
    `username`    varchar(64) CHARACTER SET utf8mb4 NOT NULL COMMENT '用户名称',
    `password`    varchar(255) CHARACTER SET utf8mb4         DEFAULT NULL COMMENT '密码',
    `salt`        varchar(255) CHARACTER SET utf8mb4         DEFAULT NULL COMMENT '盐值',
    `phone`       varchar(20) CHARACTER SET utf8mb4          DEFAULT NULL COMMENT '手机号码',
    `avatar`      varchar(255) CHARACTER SET utf8mb4         DEFAULT NULL COMMENT '头像',
    `nickname`    varchar(64) CHARACTER SET utf8mb4          DEFAULT NULL COMMENT '拓展字段:昵称',
    `name`        varchar(64) CHARACTER SET utf8mb4          DEFAULT NULL COMMENT '拓展字段:姓名',
    `email`       varchar(128) CHARACTER SET utf8mb4         DEFAULT NULL COMMENT '拓展字段:邮箱',
    `dept_id`     bigint                                     DEFAULT NULL COMMENT '部门ID',
    `create_by`   varchar(64) CHARACTER SET utf8    NOT NULL DEFAULT ' ' COMMENT '创建人',
    `update_by`   varchar(64) CHARACTER SET utf8    NOT NULL DEFAULT ' ' COMMENT '修改人',
    `create_time` datetime                                   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                   DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `lock_flag`   char(1) CHARACTER SET utf8mb4              DEFAULT '0' COMMENT'锁定标记：0(未锁定)、1(已锁定)',
    `del_flag`    char(1) CHARACTER SET utf8mb4              DEFAULT '0' COMMENT '删除标记：0(未删除)、1(已删除)',
    `wx_openid`   varchar(32) CHARACTER SET utf8mb4          DEFAULT NULL COMMENT '微信登录openId',
    `tenant_id`   bigint                            NOT NULL DEFAULT '0' COMMENT '所属租户',
    PRIMARY KEY (`user_id`) USING BTREE,
    KEY `user_wx_openid` (`wx_openid`) USING BTREE,
    KEY `user_idx1_username` (`username`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user`
VALUES (1, 'admin', '$2a$10$cE02oZ1N4mkdA6JHJUP7/uAJ3TQdVgO3kLRvLoe5KvvMU99W5r5hG', '', '18888888888', '', '管理员',
        '管理员', 'admin@mail.com', 1, ' ', ' ', '2018-04-20 07:15:18', '2022-02-18 14:02:09', '0', '0', '2303656', 1);
COMMIT;