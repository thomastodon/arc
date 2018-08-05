package app

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.integration.channel.PublishSubscribeChannel
import org.springframework.messaging.support.GenericMessage
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
@ExtendWith(SpringExtension::class)
@Tag("integration")
class ApplicationTest {

    private lateinit var mockMvc: MockMvc

    @Autowired private lateinit var context: WebApplicationContext
    @Autowired private lateinit var temperatureChannel: PublishSubscribeChannel

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build()
    }

    @Test
    @Disabled("this hangs at asyncDispatch")
    fun `get, with MockMvc`() {

        val result: MvcResult = mockMvc.perform(get("/temperatures"))
            .andExpect(request().asyncStarted())
            .andExpect(content().contentType(MediaType.APPLICATION_STREAM_JSON))
            .andReturn()

        val temperature = Temperature(degrees = 30.654)

        temperatureChannel.send(GenericMessage(temperature))

        mockMvc.perform(asyncDispatch(result))
            .andDo(print())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk)
            .andExpect(content().string("[{\"degrees\":30.654}]"))
    }
}