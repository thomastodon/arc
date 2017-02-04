package app;

import org.springframework.messaging.handler.annotation.SendTo;

public class TemperatureProducer {

    @SendTo(TemperatureSource.OUTPUT)
    public String produce() {
        return "123";
    }
}
