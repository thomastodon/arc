package app

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import java.time.LocalDateTime

@Entity
open class TemperatureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
    var time: LocalDateTime? = null
    var degrees: Double? = null

    constructor() {}

    constructor(timestamp: LocalDateTime, degrees: Double?) {
        this.time = timestamp
        this.degrees = degrees
    }
}
