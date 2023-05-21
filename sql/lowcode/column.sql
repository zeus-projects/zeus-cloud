USE zeus_lowcode;

-- ----------------------------
-- Table structure for column
-- ----------------------------
DROP TABLE IF EXISTS `column`;
create table if not exists `column`
(
    `id`                 varchar(255) not null COMMENT '属性唯一编码',
    `name`               varchar(255) not null COMMENT '属性名称',
    `comment`            varchar(255) not null COMMENT '注释',
    `data_type`          varchar(255) not null COMMENT '数据类型',
    `not_null`           tinyint      not null COMMENT '是否非空',
    `default_expression` varchar(255) null COMMENT '默认值表达式',
    `auto_increment`     tinyint      null COMMENT '自动递增',
    `create_by`          varchar(255) not null COMMENT '创建人',
    `create_time`        datetime     not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`          varchar(255) NOT NULL DEFAULT ' ' COMMENT '修改人',
    `update_time`        datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`            bit(1)       NOT NULL DEFAULT b'0' COMMENT '逻辑删除（0：正常 1：删除）',
    `tenant_id`          bigint                DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT '模型属性表';