FROM rabbitmq:3.6.0

RUN rabbitmq-plugins enable --offline rabbitmq_management
RUN rabbitmq-plugins enable --offline rabbitmq_mqtt

COPY rabbitmq.config /etc/rabbitmq/rabbitmq.config
RUN chown -R rabbitmq:rabbitmq /var/lib/rabbitmq /etc/rabbitmq &&\
	chmod 777 /var/lib/rabbitmq /etc/rabbitmq