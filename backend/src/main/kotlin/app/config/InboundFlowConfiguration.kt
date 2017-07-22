package app.config

import app.Temperature
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.amqp.dsl.Amqp
import org.springframework.integration.channel.PublishSubscribeChannel
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlows
import org.springframework.messaging.SubscribableChannel

@Configuration
open class InboundFlowConfiguration {

    @Bean
    open fun inboundFlow(
        queue: Queue,
        connectionFactory: ConnectionFactory,
        temperatureChannel: SubscribableChannel
    ): IntegrationFlow =
        IntegrationFlows
            .from(Amqp.inboundAdapter(connectionFactory, queue))
            .transform<String, Temperature> { s -> Temperature(degrees = s.toDouble()) }
            .channel(temperatureChannel)
            .get()

    @Bean
    open fun temperatureChannel() = PublishSubscribeChannel()
}
