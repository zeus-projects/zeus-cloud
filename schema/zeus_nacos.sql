use zeus_nacos;

/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = config_info   */
/******************************************/
CREATE TABLE `config_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) DEFAULT NULL,
  `content` longtext NOT NULL COMMENT 'content',
  `md5` varchar(32) DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COMMENT 'source user',
  `src_ip` varchar(50) DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) DEFAULT NULL,
  `tenant_id` varchar(128) DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) DEFAULT NULL,
  `c_use` varchar(64) DEFAULT NULL,
  `effect` varchar(64) DEFAULT NULL,
  `type` varchar(64) DEFAULT NULL,
  `c_schema` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info';

/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = config_info_aggr   */
/******************************************/
CREATE TABLE `config_info_aggr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) NOT NULL COMMENT 'datum_id',
  `content` longtext NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) DEFAULT NULL,
  `tenant_id` varchar(128) DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='增加租户字段';


/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = config_info_beta   */
/******************************************/
CREATE TABLE `config_info_beta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) DEFAULT NULL COMMENT 'app_name',
  `content` longtext NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COMMENT 'source user',
  `src_ip` varchar(50) DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_beta';

/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = config_info_tag   */
/******************************************/
CREATE TABLE `config_info_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) DEFAULT NULL COMMENT 'app_name',
  `content` longtext NOT NULL COMMENT 'content',
  `md5` varchar(32) DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COMMENT 'source user',
  `src_ip` varchar(50) DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_tag';

/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = config_tags_relation   */
/******************************************/
CREATE TABLE `config_tags_relation` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`),
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_tag_relation';

/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = group_capacity   */
/******************************************/
CREATE TABLE `group_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='集群、各Group容量信息表';

/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = his_config_info   */
/******************************************/
CREATE TABLE `his_config_info` (
  `id` bigint(64) unsigned NOT NULL,
  `nid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) NOT NULL,
  `group_id` varchar(128) NOT NULL,
  `app_name` varchar(128) DEFAULT NULL COMMENT 'app_name',
  `content` longtext NOT NULL,
  `md5` varchar(32) DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text,
  `src_ip` varchar(50) DEFAULT NULL,
  `op_type` char(10) DEFAULT NULL,
  `tenant_id` varchar(128) DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_did` (`data_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='多租户改造';


/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = tenant_capacity   */
/******************************************/
CREATE TABLE `tenant_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='租户容量信息表';


CREATE TABLE `tenant_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) default '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) default '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='tenant_info';

CREATE TABLE `users` (
	`username` varchar(50) NOT NULL PRIMARY KEY,
	`password` varchar(500) NOT NULL,
	`enabled` boolean NOT NULL
);

CREATE TABLE `roles` (
	`username` varchar(50) NOT NULL,
	`role` varchar(50) NOT NULL,
	UNIQUE INDEX `idx_user_role` (`username` ASC, `role` ASC) USING BTREE
);

CREATE TABLE `permissions` (
    `role` varchar(50) NOT NULL,
    `resource` varchar(255) NOT NULL,
    `action` varchar(8) NOT NULL,
    UNIQUE INDEX `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
);

INSERT INTO users (username, password, enabled) VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', TRUE);

INSERT INTO roles (username, role) VALUES ('nacos', 'ROLE_ADMIN');

INSERT INTO zeus_nacos.config_info (data_id, group_id, content, md5, gmt_create, gmt_modified, src_user, src_ip, app_name, tenant_id, c_desc, c_use, effect, type, c_schema) VALUES ('common-doc.yml', 'DEFAULT_GROUP', '# 解决 swagger 路径匹配错误
spring:
  mvc:
    pathmatch:
      matching-strategy: ant_path_matcher', 'e2ab1da123f11a82224b72be2da367b3', '2023-10-12 02:41:16', '2023-10-12 02:41:16', null, '192.168.0.107', '', '', null, null, null, 'yaml', null);
INSERT INTO zeus_nacos.config_info (data_id, group_id, content, md5, gmt_create, gmt_modified, src_user, src_ip, app_name, tenant_id, c_desc, c_use, effect, type, c_schema) VALUES ('zeus-gateway.yml', 'DEFAULT_GROUP', '# zeus-gateway.yml
spring:
  cloud:
    gateway:
      globalcors:
        corsConfigurations:
          \'[/**]\':
            allowedOriginPatterns: "*"
            allowed-methods: "*"
            allowed-headers: "*"
            allow-credentials: true
            exposedHeaders: "Content-Disposition,Content-Type,Cache-Control"
      httpclient:
        connect-timeout: 1000
        response-timeout: 10s
      routes:
        - id: zeus-auth
          uri: lb://zeus-auth
          predicates:
            - Path=/auth/**
        - id: zeus-upms
          uri: lb://zeus-upms-biz
          predicates:
            - Path=/admin/**
        - id: zeus-test-consumer
          uri: lb://zeus-test-consumer
          predicates:
            - Path=/consumer/**
        - id: zeus-test-producer
          uri: lb://zeus-test-producer
          predicates:
            - Path=/producer/**
          filters:
            - name: RequestRateLimiter
              args:
                deny-empty-key: true
                redis-rate-limiter.replenishRate: 1
                redis-rate-limiter.burstCapacity: 5
                redis-rate-limiter.requestedTokens: 1
                key-resolver: "#{@remoteAddrKeyResolver}"

# springdoc 自动接口文档配置
springdoc:
  api-docs:
    enabled: true
  swagger-ui:
    path: /openapi
    # 接口分组，通常一个服务一组，url 为获取 openapi 数据的接口，path 第一部分为前缀，与路由保持一致；后面为子服务配置的 openapi 接口地址
    urls:
      - name: zeus-upms-biz
        url: /admin/v3/api-docs', '228d72af7fb39160192c71e78feb3218', '2023-10-12 02:42:31', '2023-10-12 02:42:31', null, '192.168.0.107', '', '', '网关服务', null, null, 'yaml', null);
INSERT INTO zeus_nacos.config_info (data_id, group_id, content, md5, gmt_create, gmt_modified, src_user, src_ip, app_name, tenant_id, c_desc, c_use, effect, type, c_schema) VALUES ('zeus-auth.yml', 'DEFAULT_GROUP', '# 数据源
spring:
  freemarker:
    allow-request-override: false
    allow-session-override: false
    cache: true
    charset: UTF-8
    check-template-location: true
    content-type: text/html
    enabled: true
    expose-request-attributes: false
    expose-session-attributes: false
    expose-spring-macro-helpers: true
    prefer-file-system-access: true
    suffix: .ftl
    template-loader-path: classpath:/templates/', '74f53b71c7799aa754da75662378b93c', '2023-10-12 02:43:38', '2023-10-12 02:43:38', null, '192.168.0.107', '', '', '认证授权服务', null, null, 'yaml', null);
INSERT INTO zeus_nacos.config_info (data_id, group_id, content, md5, gmt_create, gmt_modified, src_user, src_ip, app_name, tenant_id, c_desc, c_use, effect, type, c_schema) VALUES ('zeus-upms-biz.yml', 'DEFAULT_GROUP', 'spring:
  autoconfigure:
    exclude: org.springframework.cloud.gateway.config.GatewayAutoConfiguration,org.springframework.cloud.gateway.config.GatewayClassPathWarningAutoConfiguration
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    druid:
      driver-class-name: com.mysql.cj.jdbc.Driver
      username: ${MYSQL_USER:zeus}
      password: ${MYSQL_PWD:NaCt9Enxkm0htIaj6wTNyp3w!W}
      url: jdbc:mysql://${MYSQL_HOST:ecs.alexchen.tech}:${MYSQL_PORT:3306}/${MYSQL_DB:zeus_upms}?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true
      stat-view-servlet:
        enabled: true
        allow: ""
        url-pattern: /druid/*
        #login-username: admin
        #login-password: admin
      filter:
        stat:
          enabled: true
          log-slow-sql: true
          slow-sql-millis: 10000
          merge-sql: false
        wall:
          config:
            multi-statement-allow: true', '23e63f09e8298d4ad87dcdad3371266a', '2023-10-12 02:45:21', '2023-10-12 02:45:21', null, '192.168.0.107', '', '', null, null, null, 'yaml', null);
INSERT INTO zeus_nacos.config_info (data_id, group_id, content, md5, gmt_create, gmt_modified, src_user, src_ip, app_name, tenant_id, c_desc, c_use, effect, type, c_schema) VALUES ('zeus-lowcode-engine.yml', 'DEFAULT_GROUP', 'spring:
  autoconfigure:
    exclude: org.springframework.cloud.gateway.config.GatewayAutoConfiguration,org.springframework.cloud.gateway.config.GatewayClassPathWarningAutoConfiguration
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    druid:
      driver-class-name: com.mysql.cj.jdbc.Driver
      username: ${MYSQL_USER:zeus}
      password: ${MYSQL_PWD:NaCt9Enxkm0htIaj6wTNyp3w!W}
      url: jdbc:mysql://${MYSQL_HOST:zeus-mysql}:${MYSQL_PORT:3306}/${MYSQL_DB:zeus_lowcode}?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true
      stat-view-servlet:
        enabled: true
        allow: ""
        url-pattern: /druid/*
        #login-username: admin
        #login-password: admin
      filter:
        stat:
          enabled: true
          log-slow-sql: true
          slow-sql-millis: 10000
          merge-sql: false
        wall:
          config:
            multi-statement-allow: true', '202e402dfb51e4081098fa7ad73f352e', '2023-10-12 02:48:21', '2023-10-12 02:48:21', null, '192.168.0.107', '', '', null, null, null, 'yaml', null);

INSERT INTO zeus_nacos.his_config_info (id, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified, src_user, src_ip, op_type, tenant_id) VALUES (0, 'common-doc.yml', 'DEFAULT_GROUP', '', '# 解决 swagger 路径匹配错误
spring:
  mvc:
    pathmatch:
      matching-strategy: ant_path_matcher', 'e2ab1da123f11a82224b72be2da367b3', '2023-10-12 02:41:18', '2023-10-12 02:41:16', null, '192.168.0.107', 'I', '');
INSERT INTO zeus_nacos.his_config_info (id, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified, src_user, src_ip, op_type, tenant_id) VALUES (0, 'zeus-gateway.yml', 'DEFAULT_GROUP', '', '# zeus-gateway.yml
spring:
  cloud:
    gateway:
      globalcors:
        corsConfigurations:
          \'[/**]\':
            allowedOriginPatterns: "*"
            allowed-methods: "*"
            allowed-headers: "*"
            allow-credentials: true
            exposedHeaders: "Content-Disposition,Content-Type,Cache-Control"
      httpclient:
        connect-timeout: 1000
        response-timeout: 10s
      routes:
        - id: zeus-auth
          uri: lb://zeus-auth
          predicates:
            - Path=/auth/**
        - id: zeus-upms
          uri: lb://zeus-upms-biz
          predicates:
            - Path=/admin/**
        - id: zeus-test-consumer
          uri: lb://zeus-test-consumer
          predicates:
            - Path=/consumer/**
        - id: zeus-test-producer
          uri: lb://zeus-test-producer
          predicates:
            - Path=/producer/**
          filters:
            - name: RequestRateLimiter
              args:
                deny-empty-key: true
                redis-rate-limiter.replenishRate: 1
                redis-rate-limiter.burstCapacity: 5
                redis-rate-limiter.requestedTokens: 1
                key-resolver: "#{@remoteAddrKeyResolver}"

# springdoc 自动接口文档配置
springdoc:
  api-docs:
    enabled: true
  swagger-ui:
    path: /openapi
    # 接口分组，通常一个服务一组，url 为获取 openapi 数据的接口，path 第一部分为前缀，与路由保持一致；后面为子服务配置的 openapi 接口地址
    urls:
      - name: zeus-upms-biz
        url: /admin/v3/api-docs', '228d72af7fb39160192c71e78feb3218', '2023-10-12 02:42:32', '2023-10-12 02:42:31', null, '192.168.0.107', 'I', '');
INSERT INTO zeus_nacos.his_config_info (id, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified, src_user, src_ip, op_type, tenant_id) VALUES (0, 'zeus-auth.yml', 'DEFAULT_GROUP', '', '# 数据源
spring:
  freemarker:
    allow-request-override: false
    allow-session-override: false
    cache: true
    charset: UTF-8
    check-template-location: true
    content-type: text/html
    enabled: true
    expose-request-attributes: false
    expose-session-attributes: false
    expose-spring-macro-helpers: true
    prefer-file-system-access: true
    suffix: .ftl
    template-loader-path: classpath:/templates/', '74f53b71c7799aa754da75662378b93c', '2023-10-12 02:43:39', '2023-10-12 02:43:38', null, '192.168.0.107', 'I', '');
INSERT INTO zeus_nacos.his_config_info (id, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified, src_user, src_ip, op_type, tenant_id) VALUES (0, 'zeus-upms-biz.yml', 'DEFAULT_GROUP', '', 'spring:
  autoconfigure:
    exclude: org.springframework.cloud.gateway.config.GatewayAutoConfiguration,org.springframework.cloud.gateway.config.GatewayClassPathWarningAutoConfiguration
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    druid:
      driver-class-name: com.mysql.cj.jdbc.Driver
      username: ${MYSQL_USER:zeus}
      password: ${MYSQL_PWD:NaCt9Enxkm0htIaj6wTNyp3w!W}
      url: jdbc:mysql://${MYSQL_HOST:ecs.alexchen.tech}:${MYSQL_PORT:3306}/${MYSQL_DB:zeus_upms}?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true
      stat-view-servlet:
        enabled: true
        allow: ""
        url-pattern: /druid/*
        #login-username: admin
        #login-password: admin
      filter:
        stat:
          enabled: true
          log-slow-sql: true
          slow-sql-millis: 10000
          merge-sql: false
        wall:
          config:
            multi-statement-allow: true', '23e63f09e8298d4ad87dcdad3371266a', '2023-10-12 02:45:21', '2023-10-12 02:45:21', null, '192.168.0.107', 'I', '');
INSERT INTO zeus_nacos.his_config_info (id, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified, src_user, src_ip, op_type, tenant_id) VALUES (0, 'zeus-lowcode-engine.yml', 'DEFAULT_GROUP', '', 'spring:
  autoconfigure:
    exclude: org.springframework.cloud.gateway.config.GatewayAutoConfiguration,org.springframework.cloud.gateway.config.GatewayClassPathWarningAutoConfiguration
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    druid:
      driver-class-name: com.mysql.cj.jdbc.Driver
      username: ${MYSQL_USER:zeus}
      password: ${MYSQL_PWD:NaCt9Enxkm0htIaj6wTNyp3w!W}
      url: jdbc:mysql://${MYSQL_HOST:zeus-mysql}:${MYSQL_PORT:3306}/${MYSQL_DB:zeus_lowcode}?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true
      stat-view-servlet:
        enabled: true
        allow: ""
        url-pattern: /druid/*
        #login-username: admin
        #login-password: admin
      filter:
        stat:
          enabled: true
          log-slow-sql: true
          slow-sql-millis: 10000
          merge-sql: false
        wall:
          config:
            multi-statement-allow: true', '202e402dfb51e4081098fa7ad73f352e', '2023-10-12 02:48:22', '2023-10-12 02:48:21', null, '192.168.0.107', 'I', '');
