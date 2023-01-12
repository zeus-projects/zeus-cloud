DROP TABLE IF EXISTS `sys_tenant`;
CREATE TABLE `sys_tenant`
(
    `id`   bigint auto_increment comment '租户id',
    `name` varchar(64) not null comment '租户名称',
    `tenant_type_id` int not null comment '租户类型id',
    `tenant_type_name` varchar(64) not null comment '租户类型名称',
    `status` char(1) DEFAULT '0' comment '状态',
    `create_by`   varchar(64) not null comment '创建人',
    `create_time` datetime    not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '创建时间',
    `update_by` varchar(64) CHARACTER SET utf8  NOT NULL DEFAULT ' ' COMMENT '修改人',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `del_flag` char(1) DEFAULT '0' comment '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '租户';