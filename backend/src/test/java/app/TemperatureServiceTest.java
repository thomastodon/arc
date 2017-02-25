package app;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TemperatureServiceTest {

    private TemperatureService temperatureService;
    @Mock private TemperatureRepository mockTemperatureRepository;
    @Mock private Clock mockClock;
    @Captor private ArgumentCaptor<Temperature> temperatureCaptor;


    @Before
    public void setUp() throws Exception {
        temperatureService = new TemperatureService(mockTemperatureRepository, mockClock);
    }

    @Test
    public void process_saves_temperature_to_Repository() {
        when(mockClock.instant()).thenReturn(Instant.ofEpochMilli(395425752000L));
        when(mockClock.getZone()).thenReturn(ZoneId.of("America/New_York"));

        temperatureService.process("32.64");

        verify(mockTemperatureRepository).save(temperatureCaptor.capture());
        Temperature temperature = temperatureCaptor.getValue();
        assertThat(temperature.getDegrees()).isEqualTo(32.64);
        assertThat(temperature.getTime()).isEqualTo(LocalDateTime.of(1982, 7, 13, 12, 29, 12));
    }
}