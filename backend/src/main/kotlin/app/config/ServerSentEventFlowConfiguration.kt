package app.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.channel.QueueChannel
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlows
import org.springframework.messaging.PollableChannel
import org.springframework.messaging.SubscribableChannel

@Configuration
open class ServerSentEventFlowConfiguration {

    @Bean
    open fun serverSentEventFlow(
        temperatureChannel: SubscribableChannel,
        serverSentEventChannel: PollableChannel
    ): IntegrationFlow =
        IntegrationFlows
            .from(temperatureChannel)
            .channel(serverSentEventChannel)
            .get()

    @Bean
    open fun serverSentEventChannel() = QueueChannel()
}
