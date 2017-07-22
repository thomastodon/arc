package app

import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE
import org.springframework.messaging.MessageHandler
import org.springframework.messaging.SubscribableChannel
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import java.io.IOException
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/temperatures")
open class TemperatureController(
    private val serverSentEventChannel: SubscribableChannel
) {

    private val logger = LoggerFactory.getLogger(TemperatureController::class.java)

    @GetMapping(produces = arrayOf(TEXT_EVENT_STREAM_VALUE))
    fun sink(): Flux<Temperature> =
        Flux.create({ sink ->
            val handler = MessageHandler { it -> sink.next(it.payload as Temperature) }
            sink.onCancel { serverSentEventChannel.unsubscribe(handler) }
            serverSentEventChannel.subscribe(handler)
        })

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
