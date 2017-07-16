package app

import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import java.time.Duration.ofSeconds
import java.util.concurrent.ThreadLocalRandom

@Component
open class TemperatureService(
    private val FakeTemperatureToAmqpGateway: FakeTemperatureToAmqpGateway
) {

    fun streamToRabbit() {
        Flux.interval(ofSeconds(1))
            .doOnNext({ FakeTemperatureToAmqpGateway.send(fakeTemperature()) })
            .subscribe()
    }

    private fun fakeTemperature() =
        ThreadLocalRandom.current().nextLong(20, 40).toString()
}
