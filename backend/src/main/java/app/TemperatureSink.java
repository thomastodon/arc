package app;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

interface TemperatureSink {
    String INPUT = "temperature";

    @Input(TemperatureSink.INPUT)
    SubscribableChannel temperature();
}
