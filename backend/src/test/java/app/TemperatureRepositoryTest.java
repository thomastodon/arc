package app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class TemperatureRepositoryTest {

    @Autowired
    private TemperatureRepository temperatureRepository;

    @Test
    public void save_savesToRepository() {
        Temperature temperature = new Temperature(LocalDateTime.of(1982, 7, 13, 12, 29, 12), 23.45);

        temperatureRepository.save(temperature);
        Temperature actualTemperature = temperatureRepository.findById(temperature.getId());

        assertThat(actualTemperature).isSameAs(temperature);
    }

    @Test
    public void findLatest_returns_the_latest_temperature() {
        Temperature temperature1 = new Temperature(LocalDateTime.of(1982, 7, 13, 12, 29, 12), 23.45);
        Temperature temperature2 = new Temperature(LocalDateTime.of(1983, 7, 13, 12, 29, 12), 23.45);
        Temperature temperature3 = new Temperature(LocalDateTime.of(1984, 7, 13, 12, 29, 12), 23.45);

        temperatureRepository.save(temperature1);
        temperatureRepository.save(temperature2);
        temperatureRepository.save(temperature3);

        Temperature latestTemperature = temperatureRepository.findLatest();

        assertThat(latestTemperature).isSameAs(temperature3);
    }
}