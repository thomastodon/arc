package app

import com.nhaarman.mockito_kotlin.mock
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.verify

class TemperatureListenerTest {

    private lateinit var temperatureListener: TemperatureListener
    private val mockTemperatureService: TemperatureService = mock()

    @BeforeEach
    fun setUp() {
        temperatureListener = TemperatureListener(mockTemperatureService)
    }

    @Test
    fun `process calls the temperatureService`() {
        temperatureListener.process("21.39")

        verify(mockTemperatureService).process("21.39")
    }
}