package app

import org.springframework.cloud.stream.annotation.Input
import org.springframework.messaging.SubscribableChannel

interface TemperatureSink {

    @Input(INPUT)
    fun temperature(): SubscribableChannel

    companion object {
        const val INPUT: String = "temperature"
    }
}
