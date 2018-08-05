package app

import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.*
import org.springframework.integration.channel.MessageChannelReactiveUtils.toPublisher
import org.springframework.messaging.PollableChannel
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.io.IOException
import javax.servlet.http.HttpServletRequest

@RestController
open class TemperatureController(
    private val serverSentEventChannel: PollableChannel
) {

    private val logger = LoggerFactory.getLogger(TemperatureController::class.java)

    @GetMapping("/temperatures", produces = arrayOf(APPLICATION_STREAM_JSON_VALUE))
    fun sink(): Flux<Temperature> = Flux
        .from(toPublisher<Temperature>(serverSentEventChannel))
        .map { it.payload }

    @GetMapping("/simple", produces = arrayOf(APPLICATION_JSON_UTF8_VALUE))
    fun simple(): Flux<Map<String, Int>> = Flux
        .range(0, 3)
        .map({ anInt -> mapOf("number" to anInt) })

    @ExceptionHandler(IOException::class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    fun exceptionHandler(e: IOException, request: HttpServletRequest) =
        if (e.message == "Broken pipe") {
            logger.info("Client has disconnected")
            null
        } else {
            HttpEntity(e.message)
        }
}
