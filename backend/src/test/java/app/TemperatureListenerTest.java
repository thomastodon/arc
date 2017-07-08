package app;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TemperatureListenerTest {

    private TemperatureListener temperatureListener;
    @Mock private TemperatureService mockTemperatureService;

    @Before
    public void setUp() throws Exception {
        temperatureListener = new TemperatureListener(mockTemperatureService);
    }

    @Test
    public void process_calls_the_temperatureService() {
        temperatureListener.process("21.39");

        verify(mockTemperatureService).process("21.39");
    }
}