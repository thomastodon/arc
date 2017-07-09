package app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.integration.annotation.IntegrationComponentScan


@SpringBootApplication
@IntegrationComponentScan
open class Application

fun main(args: Array<String>) {
    val context = SpringApplicationBuilder(Application::class.java).run(*args)

    context.getBean(TemperatureService::class.java).streamToRabbit()
}
