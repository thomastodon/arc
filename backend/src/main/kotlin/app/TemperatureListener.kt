package app

import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
open class TemperatureListener(
    private val temperatureService: TemperatureService
) {

    @StreamListener(TemperatureSink.INPUT)
    fun process(@Payload temperature: String) =
        temperatureService.process(temperature)
}
