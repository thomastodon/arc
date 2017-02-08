package app;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import static java.util.concurrent.TimeUnit.SECONDS;

@Component
class TemperatureService {

    private TemperatureSource temperatureSource;
    private Scheduler scheduler;

    @Autowired
    TemperatureService(
        TemperatureSource temperatureSource,
        Scheduler scheduler
    ) {
        this.temperatureSource = temperatureSource;
        this.scheduler = scheduler;
    }

    // TODO: this post construct is mesing everything up. We successfully bind to the channel
//    @PostConstruct
    void produceTemperatures() {
        Flowable.interval(5, SECONDS, scheduler)
            .subscribe(this::sendTemperature);
    }

    private boolean sendTemperature(Long e) {
        return temperatureSource.testChannel().send(new GenericMessage<>(e.toString()));
    }
}
