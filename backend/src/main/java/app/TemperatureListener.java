package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class TemperatureListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureListener.class);

    @StreamListener(TemperatureSink.INPUT)
    public void process(@Payload final String message) {

    }
}
