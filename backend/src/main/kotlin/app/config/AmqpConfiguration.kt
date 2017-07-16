package app.config

import org.springframework.amqp.core.AmqpAdmin
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.Binding.DestinationType.QUEUE
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.beans.factory.annotation.Value

@Configuration
open class AmqpConfiguration {

    @Bean
    open fun connectionFactory(
        @Value("\${rabbitmq.host}") rabbitmqHost: String,
        @Value("\${rabbitmq.port}") rabbitmqPort: Int,
        @Value("\${rabbitmq.username}") rabbitmqUsername: String,
        @Value("\${rabbitmq.password}") rabbitmqPassword: String
    ): ConnectionFactory =
        CachingConnectionFactory().apply {
            host = rabbitmqHost
            port = rabbitmqPort
            username = rabbitmqUsername
            setPassword(rabbitmqPassword)
        }

    @Bean
    open fun admin(connectionFactory: ConnectionFactory): AmqpAdmin =
        RabbitAdmin(connectionFactory).apply { afterPropertiesSet() }

    @Bean
    open fun queueName(@Value("\${rabbitmq.queueName}") queueName: String) = queueName

    @Bean
    open fun queue(admin: AmqpAdmin, queueName: String): Queue =
        Queue(queueName).apply { setAdminsThatShouldDeclare(admin) }

    @Bean
    open fun binding(
        admin: AmqpAdmin,
        queueName: String,
        @Value("\${rabbitmq.exchangeName}") exchangeName: String,
        @Value("\${rabbitmq.routingKey}") routingKey: String
    ): Binding =
        Binding(queueName, QUEUE, exchangeName, routingKey, null).apply {
            setAdminsThatShouldDeclare(admin)
        }
}
