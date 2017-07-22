package app

import org.springframework.stereotype.Service
import java.time.Clock
import java.time.LocalDateTime

@Service
open class TemperatureService(
    private val temperatureRepository: TemperatureRepository,
    private val clock: Clock
) {

    open fun save(temperature: Temperature) =
        temperatureRepository.save(
            TemperatureEntity(
                timestamp = LocalDateTime.now(clock),
                degrees = temperature.degrees
            )
        )

    open fun getLatest(): TemperatureEntity
        = temperatureRepository.findLatest()
}
