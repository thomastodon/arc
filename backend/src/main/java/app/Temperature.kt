package app

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import java.time.LocalDateTime

@Entity
open class Temperature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
    var time: LocalDateTime? = null
    var degrees: Double? = null

    constructor() {}

    constructor(time: LocalDateTime, degrees: Double?) {
        this.time = time
        this.degrees = degrees
    }
}
