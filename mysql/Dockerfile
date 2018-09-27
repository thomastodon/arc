FROM openjdk:8

RUN apt-get update && \
    apt-get install -y mariadb-server && \
    apt-get clean autoremove

COPY docker-entrypoint.sh /usr/local/bin/
RUN chmod +x /usr/local/bin/docker-entrypoint.sh
ENTRYPOINT ["docker-entrypoint.sh"]

EXPOSE 3306
CMD ["mysql"]