package app;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

interface Sink {
    String INPUT = "testChannel";

    @Input(Sink.INPUT)
    SubscribableChannel testChannel();
}
