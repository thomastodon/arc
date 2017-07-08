package app

import com.nhaarman.mockito_kotlin.mock
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify

class TemperatureListenerTest {

    private lateinit var temperatureListener: TemperatureListener
    private val mockTemperatureService: TemperatureService = mock()

    @Before
    fun setUp() {
        temperatureListener = TemperatureListener(mockTemperatureService)
    }

    @Test
    fun `process calls the temperatureService`() {
        temperatureListener.process("21.39")

        verify(mockTemperatureService).process("21.39")
    }
}