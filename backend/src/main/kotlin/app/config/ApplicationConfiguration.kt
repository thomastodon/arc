package app.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Clock

@Configuration
open class ApplicationConfiguration {

    @Bean
    open fun clock(): Clock = Clock.systemUTC()
}
