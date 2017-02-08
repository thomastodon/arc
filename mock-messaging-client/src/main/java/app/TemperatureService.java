package app;

import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
class TemperatureService {

    @InboundChannelAdapter(value = TemperatureSource.OUTPUT)
    public Long send() {
        return ThreadLocalRandom.current().nextLong(20, 40);
    }
}
