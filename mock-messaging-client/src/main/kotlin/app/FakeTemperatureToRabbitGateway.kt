package app

import org.springframework.integration.annotation.MessagingGateway

@MessagingGateway(defaultRequestChannel = "fakeTemperatureChannel")
interface FakeTemperatureToRabbitGateway {
    fun send(data: String)
}