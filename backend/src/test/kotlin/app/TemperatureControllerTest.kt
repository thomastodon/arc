package app

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType.parseMediaType
import org.springframework.messaging.MessageHandler
import org.springframework.messaging.SubscribableChannel
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class TemperatureControllerTest {

    private val mockSseChannel: SubscribableChannel = mock()
    private lateinit var temperatureController: TemperatureController
    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp() {
        temperatureController = TemperatureController(mockSseChannel)
        mockMvc = MockMvcBuilders.standaloneSetup(temperatureController).build()
    }

    @Test
    fun getTemperature() {

        mockMvc.perform(get("/temperatures"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(parseMediaType("text/event-stream;charset=UTF-8")))

        verify(mockSseChannel).subscribe(any<MessageHandler>())
    }
}