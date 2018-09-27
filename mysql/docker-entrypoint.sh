#!/usr/bin/env bash

if [ "$1" = 'mysql' ]; then
    service mysql start

    mysql -u root -e "CREATE USER 'root'@'%' IDENTIFIED BY '${MYSQL_ROOT_PASSWORD}';"
    mysql -u root -e "GRANT ALL ON *.* TO 'root'@'%' WITH GRANT OPTION;"

    mysql -u root -e "CREATE USER '${MYSQL_USER}'@'%' IDENTIFIED BY '${MYSQL_PASSWORD}';"
    mysql -u root -e "GRANT ALL ON *.* TO 'root'@'%' WITH GRANT OPTION;"

    mysql -u root -e "CREATE DATABASE IF NOT EXISTS ${MYSQL_DATABASE};"

    tail -f /var/log/mysql/error.log
fi

exec "$@"