# Haus

## running things locally

```bash
docker build -t thomastodon/mariadb mysql
docker push thomastodon/mariadb mysql
```

```bash
docker-compose up -d
```

The rabbitmq management plugin is at [localhost:15672](http://localhost:15672) with [Docker for Mac](https://docs.docker.com/docker-for-mac/)


Query the database here:
```bash
mysql -u root -p --protocol=tcp
```