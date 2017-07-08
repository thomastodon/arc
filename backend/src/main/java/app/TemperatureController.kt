package app

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/temperature")
open class TemperatureController(
    private val temperatureService: TemperatureService
) {

    @RequestMapping(method = arrayOf(RequestMethod.GET))
    open fun getTemperature() =
        temperatureService.getTemperature()
}
