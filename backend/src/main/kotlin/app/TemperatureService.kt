package app

import org.springframework.stereotype.Service

@Service
open class TemperatureService(
    private val temperatureRepository: TemperatureRepository
) {

    open fun save(temperature: Temperature) {

        temperatureRepository.save(
            TemperatureEntity(
                id = 0,
                degrees = temperature.degrees
            )
        )
    }

    open fun getLatest(): TemperatureEntity? = temperatureRepository.findLatest()
}
