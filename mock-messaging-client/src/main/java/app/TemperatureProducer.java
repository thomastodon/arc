package app;

import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
public class TemperatureProducer {

    private TemperatureSource temperatureSource;

    public TemperatureProducer(final TemperatureSource temperatureSource) {
        this.temperatureSource = temperatureSource;
    }

    public void produce() {
        // RX here
        temperatureSource.testChannel().send(new GenericMessage<>("20.13"));
    }
}
