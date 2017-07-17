package app

import org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE
import org.springframework.messaging.MessageHandler
import org.springframework.messaging.SubscribableChannel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/temperatures")
open class TemperatureController(
    private val serverSentEventChannel: SubscribableChannel
) {

    @GetMapping(produces = arrayOf(TEXT_EVENT_STREAM_VALUE))
    fun sink(): Flux<Temperature> =
        Flux.create({ sink ->
            val handler = MessageHandler { it -> sink.next(it.payload as Temperature) }
            sink.onCancel { serverSentEventChannel.unsubscribe(handler) }
            serverSentEventChannel.subscribe(handler)
        })

//    TODO: handle the broken pipe errors when someone unsubscribes
}
