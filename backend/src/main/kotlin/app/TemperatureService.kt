package app

import org.springframework.stereotype.Service
import java.lang.Double.parseDouble
import java.time.Clock
import java.time.LocalDateTime

@Service
open class TemperatureService(
    private val temperatureRepository: TemperatureRepository,
    private val clock: Clock
) {

    open fun process(degrees: String) =
        temperatureRepository.save(
            Temperature(
                time = LocalDateTime.now(clock),
                degrees = parseDouble(degrees)
            )
        )

    open fun getTemperature(): Temperature
        = temperatureRepository.findLatest()
}
