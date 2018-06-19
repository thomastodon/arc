package app

import com.nhaarman.mockito_kotlin.KArgumentCaptor
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("unit")
class TemperatureServiceTest {

    private val mockTemperatureRepository: TemperatureRepository = mock()
    private val temperatureEntityCaptor: KArgumentCaptor<TemperatureEntity> = argumentCaptor()
    private lateinit var temperatureService: TemperatureService

    @BeforeEach
    fun setUp() {
        temperatureService = TemperatureService(mockTemperatureRepository)
    }

    @Test
    fun `process saves temperature to Repository`() {

        temperatureService.save(Temperature(32.64))

        verify(mockTemperatureRepository).save(temperatureEntityCaptor.capture())
        val temperature = temperatureEntityCaptor.firstValue
        assertThat(temperature.degrees).isEqualTo(32.64)
    }

    @Test
    fun `getTemperature gets the latest Temperature from the repository`() {
        temperatureService.getLatest()

        verify(mockTemperatureRepository).findLatest()
    }
}