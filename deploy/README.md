# Zeus Cloud Docker Compose 部署

此目录提供本地一键启动用的 Docker Compose 配置。默认启动基础中间件：

- MySQL 8.4
- Redis 7.4
- Nacos 2.5.1
- Seata Server 2.5.0
- Nacos 配置导入任务

业务应用 `zeus-auth`、`zeus-system-server`、`zeus-gateway` 放在 `apps` profile 中，避免未打包 jar 时默认启动失败。

## 目录说明

```text
deploy/
  docker-compose.yml
  app.Dockerfile
  nacos-import-configs.sh
  nacos/
    application.yaml
    zeus-auth.yaml
    zeus-gateway.yaml
    zeus-system-server.yaml
    seata-client.yaml
    seata-server.yaml
  mysql/init/
    01-zeus-nacos.sql
    02-zeus-system.sql
    03-seata-server.sql
    04-seata-business-undo-log.sql
    99-grants.sh
  seata/registry.conf
```

MySQL 首次初始化时会自动执行 `deploy/mysql/init/*.sql`。这些脚本会创建：

- `zeus_nacos`
- `zeus_system`
- `seata_server`
- `seata_order`
- `seata_stock`

`99-grants.sh` 会把这些库的权限授予 compose 中配置的 `MYSQL_USER`，默认用户为 `zeus`。

Nacos 配置从 `deploy/nacos` 导入。普通服务配置导入到 `DEFAULT_GROUP`，`seata-*` 配置导入到 `SEATA_GROUP`。

注意：MySQL 官方镜像只会在数据目录为空时执行 `/docker-entrypoint-initdb.d` 下的初始化脚本。如果已经启动过并生成了 `zeus-mysql-data` 卷，后续修改 SQL 不会自动重新执行。

## 启动基础中间件

在项目根目录执行：

```bash
docker compose -f deploy/docker-compose.yml up -d
```

启动后访问：

- Nacos: http://localhost:8848/nacos
- Seata Console: http://localhost:7091
- MySQL: `localhost:3306`
- Redis: `localhost:6379`

Nacos 默认关闭鉴权，项目配置中的 `nacos/nacos` 用户名密码不会影响本地启动。

## 启动业务应用

先打包：

```bash
D:\Develop\Tool\apache-maven-3.8.6\bin\mvn.cmd -DskipTests package
```

再启动应用 profile：

```bash
docker compose -f deploy/docker-compose.yml --profile apps up -d --build
```

服务端口：

- Gateway: http://localhost:9999
- Auth: http://localhost:9000
- System: http://localhost:8000

## 重新初始化数据库

如果需要重新执行初始化 SQL，需要删除 MySQL 数据卷：

```bash
docker compose -f deploy/docker-compose.yml down -v
docker compose -f deploy/docker-compose.yml up -d
```

`down -v` 会删除 compose 创建的持久化卷，请确认本地数据可以丢弃后再执行。

## 常用命令

查看状态：

```bash
docker compose -f deploy/docker-compose.yml ps
```

查看日志：

```bash
docker compose -f deploy/docker-compose.yml logs -f zeus-mysql
docker compose -f deploy/docker-compose.yml logs -f zeus-nacos
docker compose -f deploy/docker-compose.yml logs -f nacos-config-importer
```

停止：

```bash
docker compose -f deploy/docker-compose.yml down
```
