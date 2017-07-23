package app

import com.nhaarman.mockito_kotlin.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@Tag("unit")
class TemperatureServiceTest {

    private val mockTemperatureRepository: TemperatureRepository = mock()
    private val mockClock: Clock = mock()
    private val temperatureEntityCaptor: KArgumentCaptor<TemperatureEntity> = argumentCaptor()
    private lateinit var temperatureService: TemperatureService

    @BeforeEach
    fun setUp() {
        temperatureService = TemperatureService(mockTemperatureRepository, mockClock)
    }

    @Test
    fun `process saves temperature to Repository`() {
        whenever(mockClock.instant()).thenReturn(Instant.ofEpochMilli(395425752000L))
        whenever(mockClock.zone).thenReturn(ZoneId.of("America/New_York"))

        temperatureService.save(Temperature(32.64))

        verify(mockTemperatureRepository).save(temperatureEntityCaptor.capture())
        val temperature = temperatureEntityCaptor.firstValue
        assertThat(temperature.degrees).isEqualTo(32.64)
        assertThat(temperature.time).isEqualTo(LocalDateTime.of(1982, 7, 13, 12, 29, 12))
    }

    @Test
    fun `getTemperature gets the latest Temperature from the repository`() {
        temperatureService.getLatest()

        verify(mockTemperatureRepository).findLatest()
    }
}