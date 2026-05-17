#!/bin/sh
set -eu

mysql -uroot -p"${MYSQL_ROOT_PASSWORD}" <<SQL
CREATE USER IF NOT EXISTS '${MYSQL_USER:-zeus}'@'%' IDENTIFIED BY '${MYSQL_PASSWORD:-zeus}';
GRANT ALL PRIVILEGES ON \`zeus_nacos\`.* TO '${MYSQL_USER:-zeus}'@'%';
GRANT ALL PRIVILEGES ON \`zeus_system\`.* TO '${MYSQL_USER:-zeus}'@'%';
GRANT ALL PRIVILEGES ON \`seata_server\`.* TO '${MYSQL_USER:-zeus}'@'%';
GRANT ALL PRIVILEGES ON \`seata_order\`.* TO '${MYSQL_USER:-zeus}'@'%';
GRANT ALL PRIVILEGES ON \`seata_stock\`.* TO '${MYSQL_USER:-zeus}'@'%';
FLUSH PRIVILEGES;
SQL
