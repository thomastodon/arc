package app;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;

@Configuration
@EnableBinding(TemperatureSource.class)
public class ApplicationConfiguration {

    @Bean
    public Scheduler scheduler() {
        return Schedulers.from(
            Executors.newSingleThreadExecutor()
        );
    }
}
