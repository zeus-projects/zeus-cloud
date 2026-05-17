#!/bin/sh
set -eu

NACOS_ADDR="${NACOS_ADDR:-http://zeus-nacos:8848}"
CONFIG_DIR="${CONFIG_DIR:-/nacos-configs}"

echo "Importing Nacos configs from ${CONFIG_DIR} to ${NACOS_ADDR}"

for file in "${CONFIG_DIR}"/*.yml "${CONFIG_DIR}"/*.yaml; do
  [ -f "${file}" ] || continue
  if [ ! -s "${file}" ]; then
    echo "Skipping empty config $(basename "${file}")"
    continue
  fi

  data_id="$(basename "${file}")"
  group="DEFAULT_GROUP"
  case "${data_id}" in
    seata-*) group="SEATA_GROUP" ;;
  esac

  echo "Importing ${data_id} to ${group}"
  curl -fsS -X POST "${NACOS_ADDR}/nacos/v1/cs/configs" \
    --data-urlencode "dataId=${data_id}" \
    --data-urlencode "group=${group}" \
    --data-urlencode "content@${file}" \
    --data-urlencode "type=yaml" >/dev/null

  if [ "${data_id}" = "seata-client.yaml" ]; then
    echo "Importing seata-client.yml to ${group}"
    curl -fsS -X POST "${NACOS_ADDR}/nacos/v1/cs/configs" \
      --data-urlencode "dataId=seata-client.yml" \
      --data-urlencode "group=${group}" \
      --data-urlencode "content@${file}" \
      --data-urlencode "type=yaml" >/dev/null
  fi
done

echo "Nacos configs imported"
