package app;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(TemperatureSink.class)
public class ApplicationConfig {

}