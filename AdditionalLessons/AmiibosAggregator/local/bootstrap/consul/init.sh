#!/bin/sh

echo "Bootstrap data - wait until port is available"
while ! nc -z localhost 8500; do
  sleep 1
done

echo "Executing consul kv command. Запишем в consul файл data.yaml"
consul kv put ru/romanorlov/amiibos-aggregator/data @/tmp/bootstrap/data.yaml