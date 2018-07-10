package app

import java.time.ZonedDateTime

data class TemperatureEntity(
    val id: Long,
    val degrees: Double,
    var timestamp: ZonedDateTime? = null
)