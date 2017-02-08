package app;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

interface TemperatureSource {
    String OUTPUT = "testChannel";

    @Output(TemperatureSource.OUTPUT)
    MessageChannel testChannel();
}
