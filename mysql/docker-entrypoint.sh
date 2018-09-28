#!/usr/bin/env bash

if [ "$1" = 'mysql' ]; then

    chmod -R 755 /var/lib/mysql/

    echo "[mysqld]" >> /etc/mysql/my.cnf
    echo "bind-address = 0.0.0.0" >> /etc/mysql/my.cnf

    service mysql start

    mysql -u root -e "CREATE USER 'root'@'%' IDENTIFIED BY '${MYSQL_ROOT_PASSWORD}';"
    mysql -u root -e "GRANT ALL ON *.* TO 'root'@'%' WITH GRANT OPTION;"

    mysql -u root -e "CREATE USER '${MYSQL_USER}'@'%' IDENTIFIED BY '${MYSQL_PASSWORD}';"
    mysql -u root -e "GRANT ALL ON *.* TO '${MYSQL_USER}'@'%' WITH GRANT OPTION;"

    mysql -u root -e "FLUSH PRIVILEGES;"

    mysql -u root -e "CREATE DATABASE IF NOT EXISTS ${MYSQL_DATABASE};"

    tail -f /var/log/mysql/error.log
fi

exec "$@"