package app

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.http.MediaType.*
import org.springframework.integration.channel.QueueChannel
import org.springframework.messaging.PollableChannel
import org.springframework.messaging.support.GenericMessage
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.returnResult
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@Tag("unit")
class TemperatureControllerTest {

    private val fakeServerSentEventChannel: PollableChannel = QueueChannel()
    private lateinit var temperatureController: TemperatureController
    private lateinit var mockMvc: MockMvc
    private lateinit var webTestClient: WebTestClient

    @BeforeEach
    fun setUp() {
        temperatureController = TemperatureController(fakeServerSentEventChannel)

        mockMvc = MockMvcBuilders.standaloneSetup(temperatureController).build()
        webTestClient = WebTestClient.bindToController(temperatureController).build()
    }

    @Test
    @Disabled("this fails without the proper Executor that we get from spring config")
    fun `get, with MockMvc`() {

        val result: MvcResult = mockMvc.perform(get("/temperatures"))
            .andExpect(request().asyncStarted())
            .andReturn()

        val temperature = Temperature(degrees = 30.654)

        fakeServerSentEventChannel.send(GenericMessage(temperature))

        mockMvc.perform(asyncDispatch(result))
            .andDo(print())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk)
            .andExpect(content().string("[{\"degrees\":30.654}]"))
    }

    @Test
    fun `get, with WebTestClient`() {

        val temperature = Temperature(degrees = 30.654)

        fakeServerSentEventChannel.send(GenericMessage(temperature))

        val publisher = webTestClient.get()
            .uri("/temperatures")
            .accept(APPLICATION_STREAM_JSON)
            .exchange()
            .expectStatus().isOk
            .returnResult<Temperature>().responseBody

        assertThat(publisher.next().block()).isEqualTo(temperature)
    }

    @Test
    fun `get simple, with MockMvc`() {

        val result: MvcResult = mockMvc.perform(get("/simple"))
            .andExpect(request().asyncStarted())
            .andReturn()

        mockMvc
            .perform(asyncDispatch(result))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk)
            .andExpect(content().string("[{\"number\":0},{\"number\":1},{\"number\":2}]"))
    }

    @Test
    fun `get simple, with WebTestClient`() {

        webTestClient.get()
            .uri("/simple")
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
            .expectBody().json("[{\"number\":0},{\"number\":1},{\"number\":2}]")

    }
}