package app

import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class TemperatureRowMapper : RowMapper<TemperatureEntity>{

    override fun mapRow(rs: ResultSet, rowNum: Int): TemperatureEntity = TemperatureEntity(
        id = rs.getLong("id"),
        degrees = rs.getDouble("degrees")
    )
}