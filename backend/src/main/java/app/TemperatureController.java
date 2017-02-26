package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temperature")
class TemperatureController {

    private TemperatureService temperatureService;

    @Autowired
    TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @RequestMapping(method = RequestMethod.GET)
    Temperature getTemperature() {
        return temperatureService.getTemperature();
    }
}
