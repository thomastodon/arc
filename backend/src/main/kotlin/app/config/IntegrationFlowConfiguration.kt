package app.config

import app.TemperatureService
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.support.converter.SimpleMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.amqp.dsl.Amqp
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlows

@Configuration
open class IntegrationFlowConfiguration {

    @Bean
    open fun temperatureFlow(
        queue: Queue,
        connectionFactory: ConnectionFactory,
        temperatureService: TemperatureService
    ): IntegrationFlow =
        IntegrationFlows
            .from(Amqp.inboundAdapter(connectionFactory, queue).messageConverter(SimpleMessageConverter()))
            .handle({ it -> temperatureService.process(it.payload.toString()) })
            .get()
}
