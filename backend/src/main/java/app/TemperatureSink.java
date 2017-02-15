package app;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

interface TemperatureSink {
    String INPUT = "temperature";

    @Input(TemperatureSink.INPUT)
    SubscribableChannel temperature();
}
