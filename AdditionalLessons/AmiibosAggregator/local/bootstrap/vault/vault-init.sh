#! /bin/sh

set -e

export VAULT_ADDR=http://vault:8200

sleep 3

vault login root

vault secrets enable kv

vault kv put kv/app/amiibos-aggregator spring.datasource.username="postgres" \
        spring.datasource.password="postgres"
