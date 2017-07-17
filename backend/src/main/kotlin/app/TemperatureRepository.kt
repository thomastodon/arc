package app

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TemperatureRepository : JpaRepository<TemperatureEntity, Long> {

    fun save(temperatureEntity: TemperatureEntity): TemperatureEntity

    @Query(value = "SELECT * FROM temperature ORDER BY time DESC LIMIT 1", nativeQuery = true)
    fun findLatest(): TemperatureEntity
}
