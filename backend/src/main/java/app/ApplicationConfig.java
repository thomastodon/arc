package app;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
@EnableBinding(TemperatureSink.class)
public class ApplicationConfig {

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }
}