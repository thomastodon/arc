package app

interface TemperatureRepository {

    fun save(temperatureEntity: TemperatureEntity)

    fun findById(id: Long): TemperatureEntity?

    fun findLatest(): TemperatureEntity?

    fun deleteAll()
}
