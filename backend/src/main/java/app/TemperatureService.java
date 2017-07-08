package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;

@Service
class TemperatureService {
    private TemperatureRepository temperatureRepository;
    private Clock clock;

    @Autowired
    TemperatureService(
        TemperatureRepository temperatureRepository,
        Clock clock
    ) {
        this.temperatureRepository = temperatureRepository;
        this.clock = clock;
    }

    void process(String degrees) {
        LocalDateTime time = LocalDateTime.now(clock);
        Temperature temperature = new Temperature(time, Double.parseDouble(degrees));
        temperatureRepository.save(temperature);
    }

    public Temperature getTemperature() {
        return temperatureRepository.findLatest();
    }
}
