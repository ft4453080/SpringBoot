#!/bin/bash

echo "$(date) - waiting for $DBHOST..."

until nc -z $DBHOST 3306; do
    sleep 1
    echo "$(date) - waiting for $DBHOST..."
done

exec "$@"
