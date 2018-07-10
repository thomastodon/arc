package app

import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import java.time.ZoneId

class TemperatureRowMapper : RowMapper<TemperatureEntity>{

    override fun mapRow(rs: ResultSet, rowNum: Int): TemperatureEntity = TemperatureEntity(
        id = rs.getLong("id"),
        degrees = rs.getDouble("degrees"),
        timestamp = rs.getTimestamp("create_timestamp")
            .toLocalDateTime()
            .atZone(ZoneId.systemDefault())
            .withZoneSameInstant(ZoneId.of("UTC"))
    )
}