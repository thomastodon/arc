package app.config

import app.Temperature
import app.TemperatureService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlows
import org.springframework.messaging.SubscribableChannel

@Configuration
open class PersistenceFlowConfiguration {

    @Bean
    open fun persistenceFlow(
        temperatureChannel: SubscribableChannel,
        temperatureService: TemperatureService
    ): IntegrationFlow =
        IntegrationFlows
            .from(temperatureChannel)
            .handle { it -> temperatureService.process(it.payload as Temperature) }
            .get()
}
