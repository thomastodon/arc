package app

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime
import javax.transaction.Transactional

@Transactional
@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
open class TemperatureRepositoryTest {

    @Autowired
    private lateinit var temperatureRepository: TemperatureRepository

    @Test
    fun `save saves a Temperature to the Repository`() {
        val temperature = Temperature(LocalDateTime.of(1982, 7, 13, 12, 29, 12), 23.45)

        temperatureRepository.save(temperature)
        val actualTemperature = temperatureRepository.getOne(temperature.id)

        assertThat(actualTemperature).isSameAs(temperature)
    }

    @Test
    fun `findLatest returns the latest Temperature`() {
        val temperature1 = Temperature(LocalDateTime.of(1982, 7, 13, 12, 29, 12), 23.45)
        val temperature2 = Temperature(LocalDateTime.of(1983, 7, 13, 12, 29, 12), 23.45)
        val temperature3 = Temperature(LocalDateTime.of(1984, 7, 13, 12, 29, 12), 23.45)

        temperatureRepository.save(temperature1)
        temperatureRepository.save(temperature2)
        temperatureRepository.save(temperature3)

        val latestTemperature = temperatureRepository.findLatest()

        assertThat(latestTemperature).isSameAs(temperature3)
    }
}