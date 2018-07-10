package app

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import java.util.*


class MySqlTemperatureRepository(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) : TemperatureRepository {

    private val temperatureRowMapper = TemperatureRowMapper()

    override fun deleteAll() {
        jdbcTemplate.update("DELETE FROM temperature", HashMap<String, Any>())
    }

    override fun findById(id: Long): TemperatureEntity? {

        val sql = "SELECT * FROM temperature WHERE id=:id"

        val parameters = mutableMapOf("id" to id)

        return jdbcTemplate.query(sql, parameters, temperatureRowMapper).elementAtOrNull(0)
    }

    override fun save(temperatureEntity: TemperatureEntity) {

        val sql = "INSERT INTO temperature (id, create_timestamp, degrees) VALUES (:id, CURRENT_TIMESTAMP(6), :degrees)"

        val parameters = mutableMapOf(
            "id" to temperatureEntity.id,
            "degrees" to temperatureEntity.degrees
        )

        jdbcTemplate.update(sql, parameters)
    }

    override fun findLatest(): TemperatureEntity? {

        val sql = "SELECT * FROM temperature ORDER BY create_timestamp DESC"

        return jdbcTemplate.query(sql, HashMap<String, Any>(), temperatureRowMapper).elementAtOrNull(0)
    }
}

