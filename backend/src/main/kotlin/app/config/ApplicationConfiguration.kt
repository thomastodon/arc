package app.config

import app.MySqlTemperatureRepository
import app.TemperatureRepository
import org.flywaydb.core.Flyway
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.datasource.SimpleDriverDataSource
import java.time.Clock

@Configuration
open class ApplicationConfiguration {

    @Bean
    open fun clock(): Clock = Clock.systemUTC()

    @Bean
    open fun jdbcTemplate(
        @Value("\${datasource.url}") datasourceUrl: String,
        @Value("\${datasource.username}") datasourceUsername: String,
        @Value("\${datasource.password}") datasourcePassword: String
    ): NamedParameterJdbcTemplate {

        val dataSource = SimpleDriverDataSource(
            com.mysql.cj.jdbc.Driver(),
            "jdbc:mysql://localhost:3306/haus?useSSL=false",
            "admin",
            "admin"
        )

        Flyway()
            .apply { this.dataSource = dataSource; isCleanOnValidationError = true }
            .run { migrate() }

        return NamedParameterJdbcTemplate(dataSource)
    }

    @Bean
    open fun temperatureRepository(jdbcTemplate: NamedParameterJdbcTemplate): TemperatureRepository =
        MySqlTemperatureRepository(jdbcTemplate)
}
