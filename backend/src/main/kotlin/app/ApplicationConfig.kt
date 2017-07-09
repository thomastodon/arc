package app

import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.support.converter.SimpleMessageConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.amqp.dsl.Amqp
import org.springframework.integration.config.EnableIntegration
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlows
import java.time.Clock

@Configuration
@EnableIntegration
open class ApplicationConfig {

    @Bean
    open fun clock() = Clock.systemUTC()

    @Bean
    open fun queue() = Queue("temperature.haus")

    @Autowired
    private lateinit var rabbitConnectionFactory: ConnectionFactory

    @Bean
    open fun temperatureFlow(
        rabbitConnectionFactory: ConnectionFactory,
        temperatureService: TemperatureService
    ): IntegrationFlow =
        IntegrationFlows
            .from(Amqp.inboundGateway(rabbitConnectionFactory, queue()).messageConverter(SimpleMessageConverter()))
            .handle({ it -> temperatureService.process(it.payload.toString()) })
            .get()
}
