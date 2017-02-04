package app;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

interface TemperatureSource {
    String OUTPUT = "testChannel";

    @Output(value = TemperatureSource.OUTPUT)
    MessageChannel testChannel();
}
