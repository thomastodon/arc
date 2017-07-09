package app

import org.springframework.amqp.core.AmqpTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.amqp.dsl.Amqp
import org.springframework.integration.channel.DirectChannel
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlows

@Configuration
open class ApplicationConfiguration {

    @Autowired private lateinit var amqpTemplate: AmqpTemplate

    @Bean
    open fun fakeTemperatureChannel() = DirectChannel()

    @Bean
    open fun fakeTemperatureToRabbitFlow(amqpTemplate: AmqpTemplate): IntegrationFlow {
        return IntegrationFlows
            .from(fakeTemperatureChannel())
            .handle(Amqp.outboundAdapter(amqpTemplate).exchangeName("temperature"))
            .get()
    }
}
