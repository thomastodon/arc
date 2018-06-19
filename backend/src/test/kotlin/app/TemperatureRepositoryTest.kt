package app

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("integration")
open class TemperatureRepositoryTest {

    private lateinit var temperatureRepository: TemperatureRepository

    @BeforeEach
    fun setUp() {
        temperatureRepository = MySqlTemperatureRepository(Utility.jdbcTemplate)
    }

    @Test
    fun `writes to and reads from the database`() {

        val temperature = TemperatureEntity(0, 23.45)

        temperatureRepository.save(temperature)

        val actualTemperature = temperatureRepository.findById(temperature.id)

        assertThat(actualTemperature).isEqualTo(temperature)
    }

    @Test
    fun `returns null if the temperature is not there`() {
        assertThat(temperatureRepository.findById(1L)).isNull()
    }

    @Test
    fun `findLatest returns the latest Temperature`() {
        val temperature1 = TemperatureEntity(0, 23.45)
        val temperature2 = TemperatureEntity(1, 23.45)
        val temperature3 = TemperatureEntity(2, 23.45)

        temperatureRepository.save(temperature1)
        temperatureRepository.save(temperature2)
        temperatureRepository.save(temperature3)

        val latestTemperature = temperatureRepository.findLatest()

        assertThat(latestTemperature).isEqualTo(temperature3)
    }

    @AfterEach
    fun tearDown() {
        temperatureRepository.deleteAll()
    }
}