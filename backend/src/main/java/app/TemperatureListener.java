package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
class TemperatureListener {

    private TemperatureService temperatureService;

    @Autowired
    TemperatureListener(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @StreamListener(TemperatureSink.INPUT)
    void process(@Payload final String temperature) {
        temperatureService.process(temperature);
    }
}
