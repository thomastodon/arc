package app.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.amqp.dsl.AmqpOutboundEndpointSpec
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlows
import org.springframework.messaging.SubscribableChannel

@Configuration
open class IntegrationFlowConfiguration {

    @Bean
    open fun fakeTemperatureToTemperatureFlow(
        fakeTemperatureChannel: SubscribableChannel,
        temperatureAdapter: AmqpOutboundEndpointSpec
    ): IntegrationFlow =
        IntegrationFlows
            .from(fakeTemperatureChannel)
            .handle(temperatureAdapter)
            .get()
}
