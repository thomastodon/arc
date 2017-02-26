package app;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

interface TemperatureSource {
    String OUTPUT = "temperature";

    @Output(value = TemperatureSource.OUTPUT)
    MessageChannel temperature();
}
