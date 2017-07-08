package app

import org.springframework.cloud.stream.annotation.Output
import org.springframework.messaging.MessageChannel

interface TemperatureSource {

    @Output(value = TemperatureSource.OUTPUT)
    fun temperature(): MessageChannel

    companion object {
        const val OUTPUT = "temperature"
    }
}
