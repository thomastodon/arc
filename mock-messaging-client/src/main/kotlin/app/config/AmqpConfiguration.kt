package app.config

import org.springframework.amqp.core.AmqpAdmin
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Exchange
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.amqp.dsl.Amqp
import org.springframework.integration.amqp.dsl.AmqpOutboundEndpointSpec
import org.springframework.integration.channel.DirectChannel
import org.springframework.retry.backoff.ExponentialBackOffPolicy
import org.springframework.retry.support.RetryTemplate

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
    open fun exchangeName(@Value("\${rabbitmq.exchangeName}") exchangeName: String) = exchangeName

    @Bean
    open fun exchange(admin: AmqpAdmin, exchangeName: String): Exchange =
        DirectExchange(exchangeName).apply { setAdminsThatShouldDeclare(admin) }

    @Bean
    open fun amqpTemplate(connectionFactory: ConnectionFactory): AmqpTemplate {
        val backOffPolicy = ExponentialBackOffPolicy().apply {
            initialInterval = 500
            multiplier = 10.0
            maxInterval = 10000
        }

        val retryTemplate = RetryTemplate().apply { setBackOffPolicy(backOffPolicy) }

        return RabbitTemplate(connectionFactory).apply { setRetryTemplate(retryTemplate) }
    }

    @Bean
    open fun fakeTemperatureChannel() = DirectChannel()

    @Bean
    open fun temperatureAdapter(
        amqpTemplate: AmqpTemplate,
        exchangeName: String,
        @Value("\${rabbitmq.routingKey}") routingKey: String
    ): AmqpOutboundEndpointSpec =
        Amqp.outboundAdapter(amqpTemplate).apply {
            exchangeName(exchangeName)
            routingKey(routingKey)
        }
}
