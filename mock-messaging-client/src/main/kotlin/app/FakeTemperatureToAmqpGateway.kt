package app

import org.springframework.integration.annotation.MessagingGateway

@MessagingGateway(defaultRequestChannel = "fakeTemperatureChannel")
interface FakeTemperatureToAmqpGateway {
    fun send(data: String)
}